package com.example.exercicio.contaBancaria.dto;

import com.example.exercicio.contaBancaria.model.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
  private Long accountNumber;
  private Integer agency;
  private String name;

  public BankAccount toDTOAccount(){
    return new BankAccount(accountNumber, agency, name);
  }
}
