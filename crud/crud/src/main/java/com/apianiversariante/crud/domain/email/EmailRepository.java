package com.apianiversariante.crud.domain.email;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, String> {
    List<Email> findAllByActiveTrue();
}
