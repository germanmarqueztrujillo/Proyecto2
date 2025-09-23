package com.example.library.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library.loan.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
  @Query("SELECT l FROM Loan l WHERE l.returned = false AND l.user.id = :userId")
  List<Loan> findByUserAndNotReturned(@Param("userId") Long userId);
}
