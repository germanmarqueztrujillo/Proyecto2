package com.example.library.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoanNotFoundExceptionById extends RuntimeException {
  public LoanNotFoundExceptionById(Long id) {
    super("Loan with id " + id + " not found");
  }
}
