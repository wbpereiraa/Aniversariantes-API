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

import java.util.Optional;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailRepository repository;
    @GetMapping
    public ResponseEntity getAllEmails(){
        var allEmails = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allEmails);
    }

    @PostMapping
    public ResponseEntity registerEmail(@RequestBody @Valid RequestEmail data){
        Email newEmail = new Email(data);
        repository.save(newEmail);
        return ResponseEntity.ok().build();
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

}
