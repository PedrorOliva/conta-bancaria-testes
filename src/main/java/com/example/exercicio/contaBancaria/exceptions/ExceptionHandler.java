package com.example.exercicio.contaBancaria.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)

  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleIdNotFound(Exception e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado!");
  }
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleInvalidIdException(Exception e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: ID inválido ");
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<String> handleInternalServerError(Exception e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: Contate o suporte! ");
  }

}
