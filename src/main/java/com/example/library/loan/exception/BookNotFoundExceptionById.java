package com.example.library.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundExceptionById extends RuntimeException {
  public BookNotFoundExceptionById(Long id) {
    super("Book with id " + id + " not found");
  }
}