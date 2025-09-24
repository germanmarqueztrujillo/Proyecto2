package com.example.library.loan.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.library.loan.dto.LoanDTO;
import com.example.library.loan.services.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Autowired private LoanService loanService; // se inyecta el mock

  @TestConfiguration
  static class MockConfig {
    @Bean
    LoanService loanService() {
      return Mockito.mock(LoanService.class);
    }
  }

  @Test
  void whenValidRequest_thenReturnOk() throws Exception {
    LoanDTO request =
        new LoanDTO(OffsetDateTime.now(), OffsetDateTime.now().plusDays(7), false, 1L, 1L);

    doNothing().when(loanService).createLoan(any(LoanDTO.class));

    mockMvc
        .perform(
            post("/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());
  }

  @Test
  void whenPastDueDate_thenReturnBad() throws Exception {
    LoanDTO request =
        new LoanDTO(OffsetDateTime.now(), OffsetDateTime.now().minusDays(7), false, 1L, 1L);

    doThrow(new IllegalArgumentException("Due date cannot be in the past"))
        .when(loanService)
        .createLoan(any(LoanDTO.class));

    mockMvc
        .perform(
            post("/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Due date cannot be in the past"));
  }

  @Test
  void whenFutureStartDate_thenReturnBad() throws Exception {
    LoanDTO request =
        new LoanDTO(
            OffsetDateTime.now().plusDays(7), OffsetDateTime.now().plusDays(7), false, 1L, 1L);

    doThrow(new IllegalArgumentException("Start date cannot be in the future"))
        .when(loanService)
        .createLoan(any(LoanDTO.class));

    mockMvc
        .perform(
            post("/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Start date cannot be in the future"));
  }
}
