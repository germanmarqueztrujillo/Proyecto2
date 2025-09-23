package com.example.library.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UnreturnedBookException extends RuntimeException {
  public UnreturnedBookException() {
    super("The book has another user");
  }
}