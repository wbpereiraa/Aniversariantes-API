package com.apianiversariante.crud.domain.email;

import jakarta.persistence.*;
import lombok.*;

@Table(name="email")
@Entity(name="email")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Email {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String email;

    private Boolean active;

    public Email(RequestEmail requestEmail){
        this.name = requestEmail.name();
        this.email = requestEmail.email();
        this.active = true;
    }
}
