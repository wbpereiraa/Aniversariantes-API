package com.apianiversariante.crud.controllers;

import com.apianiversariante.crud.domain.email.Email;
import com.apianiversariante.crud.domain.email.EmailRepository;
import com.apianiversariante.crud.domain.email.RequestEmail;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Optional;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailRepository repository;

    //Método onde se o email já estiver no BD o cupom será gerado e enviado
    @PostMapping
    public ResponseEntity registerEmail(@RequestBody @Valid RequestEmail data) {
        Optional<Email> existingEmail = repository.findByEmail(data.getEmail());
        LocalDate dataGeracaoCupom = LocalDate.now();

        if (existingEmail.isPresent()) {
            Email email = existingEmail.get();
            Random cupom = new Random();
            StringBuilder cupomNumber = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                cupomNumber.append(cupom.nextInt(99));
            }
            String generatedCupom = cupomNumber.toString();
            email.setCupom(Integer.valueOf(generatedCupom));
            email.setCupom_Data(dataGeracaoCupom);
            repository.save(email);

            String cupomMessage = "EMAIL ENVIADO COM SUCESSO! O número do seu cupom é Nº: " + generatedCupom;

            return ResponseEntity.ok().body(cupomMessage);
        } else {

            /*Cadastra o email não encontrado sem número do cupom*/
            Email newEmail = new Email(data);
            repository.save(newEmail);
            return ResponseEntity.ok(newEmail);

        }
    }

    //Método que retorna o cupom e validade pelo id do usuário
    @GetMapping("{id}")
    public ResponseEntity getCupomValidity(@PathVariable String id) {
        Optional<Email> optionalEmail = repository.findById(id);
        if (optionalEmail.isPresent()) {
            Email email = optionalEmail.get();
            LocalDate dataGeracaoCupom = email.getCupom_Data();
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataValidadeLimite = dataGeracaoCupom.plusDays(15);

            if (dataAtual.isBefore(dataValidadeLimite)) {
                long diasRestantesCupom = ChronoUnit.DAYS.between(dataAtual, dataValidadeLimite);
                return ResponseEntity.ok("EMAIL ATIVO! - O cupom é válido ate dia " + dataValidadeLimite + "." + "Faltam " + diasRestantesCupom + " dias para vencimento do cupom.");
            } else {
                return ResponseEntity.ok("EMAIL INATIVO - O cupom não está dentro da validade de 15 dias.");
            }
        } else {
            throw new EntityNotFoundException();
        }
    }
}
