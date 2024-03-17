package com.apianiversariante.crud.domain.email;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record RequestEmail(
        String id,
        @NotBlank
        String name,
        @NotNull
        String email,
        Integer cupom,
        Date cupom_data

) {
        public String getEmail() {
                return email();
        }
}
