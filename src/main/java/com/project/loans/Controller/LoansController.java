package com.project.loans.Controller;

import com.project.loans.Model.Loans;
import com.project.loans.Model.Repayment;
import com.project.loans.Service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/loan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoansController {
    @Autowired
    LoanService loanService;
    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);
    @PostMapping("/disburse")
    ResponseEntity<Loans> disburseLoan(@Validated @RequestBody Loans loan) {
        logger.info("Received Loan Request:"+loan.toString());
        return new ResponseEntity<>(loanService.saveLoan(loan), HttpStatus.OK);

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
    ResponseEntity<Repayment> repayLoan(@Validated @RequestBody Repayment repayment) {
        return new ResponseEntity<>(repayment, HttpStatus.OK);
    }
}
