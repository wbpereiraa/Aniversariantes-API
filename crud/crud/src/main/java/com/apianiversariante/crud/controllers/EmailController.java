package com.apianiversariante.crud.controllers;


import com.apianiversariante.crud.domain.email.Email;
import com.apianiversariante.crud.domain.email.EmailRepository;
import com.apianiversariante.crud.domain.email.RequestEmail;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Random;

import java.util.Optional;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailRepository repository;

    /*
    //Método que retorna todos emails no Banco de Dados!
    @GetMapping
    public ResponseEntity getAllEmails(){
        var allEmails = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allEmails);
    }
    */

    //Método onde se o email já estiver no BD o cupom será gerado e enviado com válidade de 15 dias
    @PostMapping
    public ResponseEntity registerEmail(@RequestBody @Valid RequestEmail data) {
        if (emailExists(data.getEmail())) {
            Random cupom = new Random();
            StringBuilder cupomNumber = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                cupomNumber.append(cupom.nextInt(99));
            }
            /*LocalDate dataAtual = LocalDate.now();
            LocalDate dataValidade = dataAtual.plusDays(15);
            String dataValidadeFormatada = dataValidade.format(DateTimeFormatter.ISO_DATE);*/

            String cupomMessage = "EMAIL ENVIADO COM SUCESSO! O número do seu cupom é Nº: " + cupomNumber.toString();
            return ResponseEntity.ok().body(cupomMessage);
        } else {

            /*Caso queira cadastrar o email não encontrado é só fazer essa alteração abaixo*/
            Email newEmail = new Email(data);
            repository.save(newEmail);
            return ResponseEntity.ok(newEmail);

            //throw new EntityNotFoundException();
        }
    }

    //Método que retorna os emails pelo id do usuário
    @GetMapping("/{id}")
    public ResponseEntity getAllEmails(@PathVariable String id){
        Optional<Email> optionalEmail = repository.findById(id);
        if (optionalEmail.isPresent()) {
            Email email = optionalEmail.get();
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataValidade = dataAtual.plusDays(15);
            String dataValidadeFormatada = dataValidade.format(DateTimeFormatter.ISO_DATE);
            //System.out.println("A validade do seu cupom é até o dia: " + dataValidadeFormatada);

            return ResponseEntity.ok().body("Email Válido! Validade do cupom: " + dataValidadeFormatada);


        } else {
            throw new EntityNotFoundException("Email Inválido!");
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateEmail(@RequestBody @Valid RequestEmail data){
        Optional<Email> optionalEmail = repository.findById(data.id());
        if (optionalEmail.isPresent()) {
            Email email = optionalEmail.get();
            email.setName(data.name());
            email.setEmail(data.email());
            return ResponseEntity.ok(email);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteEmail(@PathVariable String id){
        Optional<Email> optionalEmail = repository.findById(id);
        if (optionalEmail.isPresent()) {
            Email email = optionalEmail.get();
            email.setActive(false);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }

    private boolean emailExists(String email) {
        return repository.existsByEmail(email);
    }

}
