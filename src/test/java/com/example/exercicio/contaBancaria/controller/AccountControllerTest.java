package com.example.exercicio.contaBancaria.controller;

import com.example.exercicio.contaBancaria.model.BankAccount;
import com.example.exercicio.contaBancaria.repository.AccountRepository;
import com.example.exercicio.contaBancaria.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AccountService accountService;

  @MockBean
  private AccountRepository accountRepository;

  @DisplayName("Find all accounts")
  @Test
  public void testListAccounts() throws Exception {
    List<BankAccount> accountList = new ArrayList<>();
    BankAccount account1 = new BankAccount();
    account1.setAccountNumber(1111L);
    account1.setAgency(2222);
    account1.setName("Usuario Um");
    account1.setBalance(100.00);

    BankAccount account2 = new BankAccount();
    account2.setAccountNumber(0000L);
    account2.setAgency(9999);
    account2.setName("Usuario Dois");
    account2.setBalance(200.00);

    accountList.add(account1);
    accountList.add(account2);

    Mockito.when(accountService.findAllAccounts()).thenReturn(accountList);

    mockMvc.perform(get("/api/account"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$[0].accountNumber").value(1111))
        .andExpect(jsonPath("$[0].agency").value(2222))
        .andExpect(jsonPath("$[0].name").value("Usuario Um"))
        .andExpect(jsonPath("$[1].accountNumber").value(0000))
        .andExpect(jsonPath("$[1].agency").value(9999))
        .andExpect(jsonPath("$[1].name").value("Usuario Dois"));
  }

  @DisplayName("Find account by ID")
  @Test
  public void findAccountById() throws Exception {
    BankAccount account = new BankAccount(1L, 1111L, 2222, "Usuario Um",
        100.00);

    Mockito.when(accountService.findOneAccount(account.getId())).thenReturn(Optional.of(account));

    mockMvc.perform(get("/api/account/{id}", 1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.accountNumber").value(1111))
        .andExpect(jsonPath("$.agency").value(2222))
        .andExpect(jsonPath("$.name").value("Usuario Um"));
  }

  @DisplayName("Find account when ID not found")
  @Test
  public void findAccountIdNotFound() throws Exception {
    mockMvc.perform(get("/api/account/{id}", 1L))
        .andExpect(status().isNotFound());
  }

  @DisplayName("Create a new account")
  @Test
  public void testCreateAccount() throws Exception {
    BankAccount account = new BankAccount();
    account.setAccountNumber(1111L);
    account.setAgency(2222);
    account.setName("Usuario Um");
    account.setBalance(100.00);

    Mockito.when(accountService.create(any())).thenReturn(account);

    mockMvc.perform(post("/api/account")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"accountNumber\": \"1111\", \"agency\": \"2222\", \"name\": \"Usuario Um\", " +
            "\"balance\": \"100.00\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.accountNumber").value(1111))
        .andExpect(jsonPath("$.agency").value(2222))
        .andExpect(jsonPath("$.name").value("Usuario Um"));

  }

  @DisplayName("Making a deposit")
  @Test
  public void testMakingDeposit() throws Exception {
    BankAccount account = new BankAccount(1L, 1111L, 2222, "Usuario Um",
        100.00);

    Mockito.when(accountService.bankDeposit(1L, 50.00, account)).thenReturn(account);

    mockMvc.perform(patch("/api/account/deposit/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(String.valueOf(50.00)))
        .andExpect(status().isOk());
  }

  @DisplayName("Making a draft")
  @Test
  public void testMakingDraft() throws Exception {
    BankAccount account = new BankAccount(1L, 1111L, 2222, "Usuario Um",
        100.00);

    Mockito.when(accountService.bankDraft(1L, 50.00, account)).thenReturn(account);

    mockMvc.perform(patch("/api/account/draft/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(String.valueOf(50.00)))
        .andExpect(status().isOk());
  }


  @DisplayName("Delete account")
  @Test
  public void testDeleteAccount() throws Exception {
    mockMvc.perform(delete("/api/account/{id}" ,1L))
        .andExpect(status().isOk());
  }
}
