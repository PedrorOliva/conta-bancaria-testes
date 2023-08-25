package com.example.exercicio.contaBancaria.repository;

import com.example.exercicio.contaBancaria.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<BankAccount, Long> {

}
