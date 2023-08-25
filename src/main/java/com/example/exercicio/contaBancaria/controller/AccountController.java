package com.example.exercicio.contaBancaria.controller;

import com.example.exercicio.contaBancaria.dto.AccountDTO;
import com.example.exercicio.contaBancaria.dto.AccountResponse;
import com.example.exercicio.contaBancaria.model.BankAccount;
import com.example.exercicio.contaBancaria.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/account")
public class AccountController {
  @Autowired
  AccountService accountService;

  @PostMapping
  public ResponseEntity<AccountResponse> registerAccount(@RequestBody AccountDTO dto){
    BankAccount bankAccount = accountService.create(dto.toDTOAccount());
    return new ResponseEntity<>(AccountResponse.toAccountDTO(bankAccount), HttpStatus.CREATED);
  }

  @GetMapping
  public List<AccountDTO> findAllAccounts(){
    List<BankAccount> accounts = accountService.findAllAccounts();
    List<AccountDTO> accountDTO = new ArrayList<>();

    for(BankAccount account: accounts){
      AccountDTO dto = new AccountDTO();
      dto.setAccountNumber(account.getAccountNumber());
      dto.setAgency(account.getAgency());
      dto.setName(account.getName());
      accountDTO.add(dto);
    }
    return accountDTO;
  }

  @GetMapping("{id}")
  public ResponseEntity<?> findAccountByID(@PathVariable Long id){
    Optional<BankAccount> optionalAccount = accountService.findOneAccount(id);
    if(optionalAccount.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado!");
    }
    return ResponseEntity.ok(optionalAccount.get());
  }

  @PatchMapping("/deposit/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BankAccount bankDeposit(@PathVariable Long id, @RequestBody  Double providedValue,
                                 BankAccount bankAccount){
    return accountService.bankDeposit(id, providedValue, bankAccount);
  }

  @PatchMapping("/draft/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BankAccount bankDraft(@PathVariable Long id, @RequestBody Double providedValue,
                               BankAccount bankAccount){
    return accountService.bankDraft(id, providedValue, bankAccount);
  }

  @DeleteMapping("{id}")
  public void deleteAccount(@PathVariable Long id) {
    accountService.deleteAccount(id);
  }
}
