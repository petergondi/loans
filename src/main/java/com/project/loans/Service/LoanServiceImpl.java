package com.project.loans.Service;

import com.project.loans.Model.Loans;
import com.project.loans.Model.Repayment;
import com.project.loans.Repository.LoansRepository;
import com.project.loans.Repository.RepaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LoanServiceImpl implements LoanService{
    @Autowired
    LoansRepository loanRepository;
    @Autowired
    RepaymentRepository repaymentRepository;
    @Override
    public Loans saveLoan(Loans loan) {
        return loanRepository.save(loan);
    }
    @Override
    public List<Loans> getAllLoan() {
        return loanRepository.findAll();
    }
    @Override
    public Loans getOneLoan(Long id) {
        Optional<Loans> loan = loanRepository.findById(id);
        return loan.orElse(null);
    }
    @Override
    public Repayment repayLoan(Repayment repayment) {
        //TODO
        //if running balance is zero reject the payment
        //if no loan id exist reject the repayment
        //else add the repayment to repayment table and subtract the running loan balance from the loans table
        return repaymentRepository.save(repayment);
    }
}
