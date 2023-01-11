package com.project.loans.Service;

import com.project.loans.Model.Loans;
import com.project.loans.Model.Sms;

public interface SmsService {
    Sms saveSms(Sms sms);

    Sms getOneSms(Long id);

    Sms getAllSmsForPhone(Long id);
}
