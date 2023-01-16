package com.project.loans.Repository;

import com.project.loans.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    @Query(value = "SELECT * FROM contacts WHERE msisdn=:contact", nativeQuery = true)
    Optional<Contact> findByPhone(@Param("contact") String contact);

}
