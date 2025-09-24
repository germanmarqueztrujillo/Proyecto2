package com.example.library.loan.repository;

import com.example.library.loan.model.Loan;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
  @Query("SELECT l FROM Loan l WHERE l.returned = false AND l.user.id = :userId")
  List<Loan> findByUserAndNotReturned(@Param("userId") Long userId);

  @Query(
      "SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END "
          + "FROM Loan l "
          + "WHERE l.book.id = :bookId AND l.returned = false")
  boolean existsUnreturnedLoanByBookId(@Param("bookId") Long bookId);

  @Query(
      "SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END "
          + "FROM Loan l "
          + "WHERE l.user.id = :userId AND l.book.id = :bookId AND l.returned = false")
  boolean userHaveBookByUserIdAndBookId(@Param("bookId") Long bookId, @Param("userId") Long userId);

  @Modifying
  @Transactional
  @Query("UPDATE Loan l SET l.returned = true WHERE l.id = :loanId")
  void updateLoanReturnedById(@Param("loanId") Long loanId);
}
