package com.project.loans.Service;

import com.project.loans.Model.Contact;
import com.project.loans.Model.Loans;
import com.project.loans.Model.Repayment;

import java.util.List;

public interface LoanService {
    Loans saveLoan(Loans loan);

    List<Loans> getAllLoan();

    Loans getOneLoan(Long id);

    Repayment repayLoan(Repayment repayment);

    int checkLoan(int contactId);

    int checkLoanByID(int Id,int contactID);
}
