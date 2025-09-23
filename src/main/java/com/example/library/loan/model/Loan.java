package com.example.library.loan.model;

import java.util.Date;

import com.example.library.book.model.Book;
import com.example.library.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
  private Date startDate;

  @NotNull
  private Date dueDate;

  @NotNull
  private Boolean returned;

  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;

  @ManyToOne
  @JoinColumn(name="book_id", nullable=false)
  private Book book;
}
