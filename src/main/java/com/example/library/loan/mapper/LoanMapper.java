package com.example.library.loan.mapper;

import com.example.library.loan.dto.LoanDTO;
import com.example.library.loan.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "book", ignore = true)
  Loan toEntity(LoanDTO dto);

  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "book.id", target = "bookId")
  LoanDTO toDTO(Loan book);
}
