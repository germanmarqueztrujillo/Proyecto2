package com.example.library.book.model;

import java.util.List;

import com.example.library.loan.model.Loan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(nullable = false)
  private String title;

  @NotBlank
  @Column(nullable = false)
  private String author;

  @NotBlank
  @Column(nullable = false, unique = true)
  private String isbn;

  @OneToMany(mappedBy="book")
  private List<Loan> loans;
}
