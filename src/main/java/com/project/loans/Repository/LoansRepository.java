package com.project.loans.Repository;

import com.project.loans.Model.Contact;
import com.project.loans.Model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loans,Long> {
    @Query(value = "SELECT * FROM loans WHERE contact_id=:contactId AND status='ACTIVE'", nativeQuery = true)
    int findActiveLoan(@Param("contactId") int contactId);
    @Query(value = "SELECT * FROM loans WHERE id=:Id AND status='ACTIVE'", nativeQuery = true)
    int findActiveLoanById(@Param("Id") int Id,@Param("Id") int contact_id);
}
