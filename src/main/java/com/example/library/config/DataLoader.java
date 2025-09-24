package com.example.library.config;

import com.example.library.book.model.Book;
import com.example.library.book.repository.BookRepository;
import com.example.library.user.model.User;
import com.example.library.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final UserRepository userRepository;
  private final BookRepository bookRepository;

  public DataLoader(UserRepository userRepository, BookRepository bookRepository) {
    this.userRepository = userRepository;
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) {
    User alice = new User();
    alice.setName("Alice");
    alice.setEmail("alice@test.com");
    userRepository.save(alice);

    User bob = new User();
    bob.setName("Bob");
    bob.setEmail("bob@test.com");
    userRepository.save(bob);

    Book cleanCode = new Book();
    cleanCode.setTitle("Clean Code");
    cleanCode.setAuthor("Robert C. Martin");
    cleanCode.setIsbn("978-0132350884");
    bookRepository.save(cleanCode);

    Book effectiveJava = new Book();
    effectiveJava.setTitle("Effective Java");
    effectiveJava.setAuthor("Joshua Bloch");
    effectiveJava.setIsbn("978-0134685991");
    bookRepository.save(effectiveJava);
  }
}
