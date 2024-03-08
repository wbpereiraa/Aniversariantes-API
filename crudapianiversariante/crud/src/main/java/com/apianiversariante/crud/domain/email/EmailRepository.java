package com.apianiversariante.crud.domain.email;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, String> {
    List<Email> findAllByActiveTrue();
}
