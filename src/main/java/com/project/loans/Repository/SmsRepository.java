package com.project.loans.Repository;

import com.project.loans.Model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<Sms,Long> {
}