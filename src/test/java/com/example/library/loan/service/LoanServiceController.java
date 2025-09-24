package com.example.library.loan.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.library.book.model.Book;
import com.example.library.book.repository.BookRepository;
import com.example.library.loan.dto.LoanDTO;
import com.example.library.loan.exception.UnreturnedBookException;
import com.example.library.loan.exception.UserHaveBookException;
import com.example.library.loan.model.Loan;
import com.example.library.loan.repository.LoanRepository;
import com.example.library.loan.services.LoanService;
import com.example.library.user.model.User;
import com.example.library.user.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LoanServiceTest {

  @Mock private LoanRepository loanRepository;

  @Mock private BookRepository bookRepository;

  @Mock private UserRepository userRepository;

  @InjectMocks private LoanService loanService;

  public LoanServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenBookAlreadyLoanedByOtherUser_thenThrowException() {
    Book book = new Book(1L, "Clean Code", "Robert C. Martin", "123456", new ArrayList<Loan>());

    when(loanRepository.existsUnreturnedLoanByBookId(book.getId())).thenReturn(true);

    LoanDTO request =
        new LoanDTO(OffsetDateTime.now(), OffsetDateTime.now().minusDays(7), false, 1L, 1L);

    UnreturnedBookException ex =
        assertThrows(
            UnreturnedBookException.class,
            () -> {
              loanService.createLoan(request);
            });

    assertEquals("The book has another user", ex.getMessage());
  }

  @Test
  void whenBookAlreadyLoanedBySameUser_thenThrowException() {
    Book book = new Book(1L, "Clean Code", "Robert C. Martin", "123456", new ArrayList<Loan>());
    User user = new User(1L, "Alice", "alice@example.com", new ArrayList<Loan>());

    when(loanRepository.userHaveBookByUserIdAndBookId(book.getId(), user.getId())).thenReturn(true);

    LoanDTO request =
        new LoanDTO(OffsetDateTime.now(), OffsetDateTime.now().minusDays(7), false, 1L, 1L);

    UserHaveBookException ex =
        assertThrows(
            UserHaveBookException.class,
            () -> {
              loanService.createLoan(request);
            });

    assertEquals("The user already have this book", ex.getMessage());
  }
}
