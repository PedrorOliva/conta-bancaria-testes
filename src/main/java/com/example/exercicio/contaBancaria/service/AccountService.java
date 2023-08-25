package com.example.exercicio.contaBancaria.service;

import com.example.exercicio.contaBancaria.model.BankAccount;
import com.example.exercicio.contaBancaria.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
  @Autowired
  AccountRepository accountRepository;

  public List<BankAccount> findAllAccounts() {
    return accountRepository.findAll();
  }

  public Optional<BankAccount> findOneAccount(Long id) {
    return accountRepository.findById(id);
  }

  public BankAccount create(BankAccount bankAccount) {
    return accountRepository.save(bankAccount);
  }

  public BankAccount bankDeposit(Long id, Double providedValue, BankAccount bankAccount) {
    BankAccount account = findOneAccount(id).get();
    if (bankAccount.getId() != null) {
      double currentBalance = account.getBalance();
      account.setBalance(currentBalance + providedValue);
    }
    return accountRepository.save(account);
  }

  public BankAccount bankDraft(Long id, Double providedValue, BankAccount bankAccount) {
    BankAccount account = findOneAccount(id).get();
    if (bankAccount.getId() != null) {
      double currentBalance = account.getBalance();
      account.setBalance(currentBalance - providedValue);
    }
    return accountRepository.save(account);
  }

  public void deleteAccount(Long id) {
    accountRepository.deleteById(id);
  }

}
