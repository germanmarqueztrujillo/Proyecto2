package com.example.library.loan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.loan.dto.LoanDTO;
import com.example.library.loan.services.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {
  
  private final LoanService loanService;

  public LoanController (LoanService loanService) {
    this.loanService = loanService;
  }

  @PostMapping
  public void createLoan(LoanDTO loanDTO) {
    loanService.createLoan(loanDTO);
  }

  @GetMapping("/{id}")
  public LoanDTO getLoan(@PathVariable Long loanId) {
    return loanService.getLoanById(loanId);
  }

  @GetMapping
  public List<LoanDTO> getLoans() {
    return loanService.getLoans();
  }

  @PatchMapping("/{id}/return")
  public void updateLoanReturnedById(@PathVariable Long LoanId) {
    loanService.updateLoanReturnedById(LoanId);
  }
}
