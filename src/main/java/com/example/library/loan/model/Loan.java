package com.example.library.loan.model;

import java.time.OffsetDateTime;

import com.example.library.book.model.Book;
import com.example.library.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Loan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotNull
  @Past
  private OffsetDateTime startDate;

  @NotNull
  @Future
  private OffsetDateTime dueDate;

  @NotNull
  private Boolean returned = false;

  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;

  @ManyToOne
  @JoinColumn(name="book_id", nullable=false)
  private Book book;
}
