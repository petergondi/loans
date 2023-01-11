package com.project.loans.Service;

import com.project.loans.Model.Sms;
import com.project.loans.Repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SmsServiceImpl implements SmsService{
    @Autowired
    SmsRepository smsRepository;
    @Override
    public Sms saveSms(Sms sms) {
        return smsRepository.save(sms);
    }
    @Override
    public Sms getOneSms(Long id) {
        Optional<Sms> sms = smsRepository.findById(id);
        return sms.orElse(null);
    }
    @Override
    public Sms getAllSmsForPhone(Long id) {
        Optional<Sms> sms = smsRepository.findById(id);
        return sms.orElse(null);
    }
}
