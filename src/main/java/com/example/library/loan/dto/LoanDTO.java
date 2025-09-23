package com.example.library.loan.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanDTO {
  @NotNull
  @Past
  private OffsetDateTime startDate;

  @NotNull
  @Future
  private OffsetDateTime dueDate;

  @NotNull
  private Boolean returned = false;

  @NotNull
  private Long userId;
  
  @NotNull
  private Long bookId;
}
