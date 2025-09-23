package com.example.library.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.loan.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
  
}
