package com.example.library.loan.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
  @NotNull @Past private OffsetDateTime startDate;

  @NotNull @Future private OffsetDateTime dueDate;

  @NotNull private Boolean returned = false;

  @NotNull private Long userId;

  @NotNull private Long bookId;
}
