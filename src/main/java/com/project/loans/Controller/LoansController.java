package com.project.loans.Controller;

import com.project.loans.Model.Loans;
import com.project.loans.Model.Repayment;
import com.project.loans.Model.ResponseHandler;
import com.project.loans.Model.Sms;
import com.project.loans.Service.ContactService;
import com.project.loans.Service.LoanService;
import com.project.loans.Service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/loan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoansController {
    @Autowired
    LoanService loanService;
    @Autowired
    ContactService contactService;
    @Autowired
    SmsService smsService;
    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);
    @PostMapping("/disburse")
    ResponseEntity<Object> disburseLoan(@Validated @RequestBody Loans loan) {
        String message = null;
        Sms notify=new Sms();
        logger.info("Received Loan Request:"+loan.toString());
        if(contactService.getOneContact((long) loan.getContactId())==null) {
            return ResponseHandler.generateResponse("The contact do not exist please register first!!", HttpStatus.MULTI_STATUS, null);
        }
        if(loanService.checkLoan(loan.getContactId())==0) {
            message = "Your loan request for loan id:" + loan.getContactId() + " amount " + loan.getAmount() + " has been approved";
            notify.setMessage(message);
            notify.setContactId(loan.getContactId());
            smsService.saveSms(notify);
            loan.setRunningBalance(loan.getAmount());
            loan.setStatus("ACTIVE");
            loan.setSmsId((int) notify.getId());
            return ResponseHandler.generateResponse("Loan Disbursed Successfully!", HttpStatus.OK, loanService.saveLoan(loan));

        }
        return ResponseHandler.generateResponse("You have an existing loan please complete it first!!", HttpStatus.MULTI_STATUS, null);
    }
    @GetMapping("/single/{id}")
    ResponseEntity<Loans> getOneLoan(@PathVariable Long id) {
        return new ResponseEntity<>(loanService.getOneLoan(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    ResponseEntity<Loans> getAllLoan(@PathVariable Long id) {
        return new ResponseEntity<>(loanService.getOneLoan(id), HttpStatus.OK);
    }
    @PostMapping("/repay")
    ResponseEntity<Object> repayLoan(@Validated @RequestBody Repayment repayment) {
        logger.info("Received Loan Repayment Request:"+repayment.toString());
        Double new_balance;
        String message;
        if(loanService.checkLoanByID(repayment.getLoanId(),repayment.getContactId())==0)
            return ResponseHandler.generateResponse("The repayment request cannot be completed since you do not have a loan!!", HttpStatus.MULTI_STATUS, null);

        Loans existing_loan=loanService.getOneLoan((long) repayment.getLoanId());
        if(repayment.getAmount()>existing_loan.getRunningBalance())
            return ResponseHandler.generateResponse("Loan Overpayment Not allowed,please pay "+existing_loan.getRunningBalance()+" or less", HttpStatus.MULTI_STATUS, null);

        new_balance=existing_loan.getRunningBalance()- repayment.getAmount();
        existing_loan.setRunningBalance(new_balance);
        if(new_balance==0)
            existing_loan.setStatus("COMPLETED");
        Sms notify=new Sms();
        message="Your loan repayment for loan id:"+repayment.getContactId()+" amount"+repayment.getAmount()+" has been received";
        notify.setMessage(message);
        notify.setContactId(repayment.getContactId());
        smsService.saveSms(notify);
        loanService.saveLoan(existing_loan);
        repayment.setSmsId((int) notify.getId());
        return ResponseHandler.generateResponse("Loan repayed Successfully!", HttpStatus.OK, loanService.repayLoan(repayment));

    }
}
