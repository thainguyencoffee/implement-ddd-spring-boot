package com.example.lending.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<Loan, Loan.LoanIdentifier> {
    @Query("select count(*) from Loan where copyId = :copyId and returnedAt is null")
    boolean isAvailable(CopyId copyId);
}
