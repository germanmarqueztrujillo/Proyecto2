package com.example.library.loan.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.library.book.model.Book;
import com.example.library.book.repository.BookRepository;
import com.example.library.loan.dto.LoanDTO;
import com.example.library.loan.exception.BookNotFoundExceptionById;
import com.example.library.loan.exception.LoanNotFoundExceptionById;
import com.example.library.loan.exception.UnreturnedBookException;
import com.example.library.loan.exception.UserHaveBookException;
import com.example.library.loan.exception.UserNotFoundExceptionById;
import com.example.library.loan.mapper.LoanMapper;
import com.example.library.loan.model.Loan;
import com.example.library.loan.repository.LoanRepository;
import com.example.library.user.model.User;
import com.example.library.user.repository.UserRepository;

@Service
public class LoanService {

  private final LoanRepository loanRepository;
  private final UserRepository userRepository;
  private final BookRepository bookRepository;
  private final LoanMapper loanMapper;

  public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, LoanMapper loanMapper) {
    this.loanRepository = loanRepository;
    this.userRepository = userRepository;
    this.bookRepository = bookRepository;
    this.loanMapper = loanMapper;
  }

  public void createLoan(LoanDTO loanDTO) {
    if (loanRepository.userHaveBookByUserIdAndBookId(loanDTO.getBookId(), loanDTO.getUserId())) {
      throw new UserHaveBookException();
    } else if (loanRepository.existsUnreturnedLoanByBookId(loanDTO.getBookId())) {
      throw new UnreturnedBookException();
    }

    Loan loan = loanMapper.toEntity(loanDTO);

    User user = userRepository.findById(loanDTO.getUserId())
            .orElseThrow(() -> new UserNotFoundExceptionById(loanDTO.getUserId()));
    Book book = bookRepository.findById(loanDTO.getBookId())
            .orElseThrow(() -> new BookNotFoundExceptionById(loanDTO.getBookId()));

    loan.setUser(user);
    loan.setBook(book);

    loanRepository.save(loan);
  }

  public LoanDTO getLoanById(Long id) {
    Loan loan = loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundExceptionById(id));
    return loanMapper.toDTO(loan);
  }
  
  public List<LoanDTO> getLoans() {
    List<Loan> loans = loanRepository.findAll();
    return loans.stream().map(loanMapper::toDTO).collect(Collectors.toList());
  }

  public void updateLoanReturnedById(Long loanId) {
    loanRepository.updateLoanReturnedById(loanId);
  }
}
