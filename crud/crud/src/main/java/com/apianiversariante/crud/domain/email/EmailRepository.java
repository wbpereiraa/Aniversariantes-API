package com.apianiversariante.crud.domain.email;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, String> {

    Optional<Email> findByEmail(String email);
}
