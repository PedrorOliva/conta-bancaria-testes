package com.example.exercicio.contaBancaria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)

  private Long accountNumber;
  @Column(nullable = false)

  private Integer agency;
  @Column(length = 50, nullable = false)

  private String name;
  private Double balance = 0.0;

  public BankAccount(Long accountNumber, Integer agency, String name) {
    this.accountNumber = accountNumber;
    this.agency = agency;
    this.name = name;
  }

}
