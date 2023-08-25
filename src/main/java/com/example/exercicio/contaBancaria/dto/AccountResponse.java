package com.example.exercicio.contaBancaria.dto;

import com.example.exercicio.contaBancaria.model.BankAccount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountResponse {
  private Long accountNumber;
  private Integer agency;
  private String name;

  public static AccountResponse toAccountDTO(BankAccount bankAccount){
    return new AccountResponse(bankAccount.getAccountNumber(), bankAccount.getAgency(), bankAccount.getName());
  }
}
