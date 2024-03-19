package com.apianiversariante.crud.domain.email;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestEmail(
        String id,
        @NotBlank
        String name,
        @NotNull
        String email,
        Integer cupom

) {
        public String getEmail() {
                return email();
        }
}
