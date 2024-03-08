package com.apianiversariante.crud.controllers;


import com.apianiversariante.crud.domain.email.Email;
import com.apianiversariante.crud.domain.email.EmailRepository;
import com.apianiversariante.crud.domain.email.RequestEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/email")
public class EmailControllers {

    @Autowired
    private EmailRepository repository;

    @GetMapping("/cupons")
    public ResponseEntity getAllEmails() {
        var allEmails = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allEmails);
    }

    @PostMapping
    public ResponseEntity registerEmail(@RequestBody @Valid RequestEmail data) {
        Email newEmail = new Email(data);
        repository.save(newEmail);
        return ResponseEntity.ok().build();
    }
}
