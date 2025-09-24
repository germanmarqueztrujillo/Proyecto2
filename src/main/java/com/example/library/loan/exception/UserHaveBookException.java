package com.example.library.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserHaveBookException extends RuntimeException {
  public UserHaveBookException() {
    super("The user already have this book");
  }
}
