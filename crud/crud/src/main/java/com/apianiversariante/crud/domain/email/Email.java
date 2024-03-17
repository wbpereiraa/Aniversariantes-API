package com.apianiversariante.crud.domain.email;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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

    private Integer cupom;

    private LocalDate cupom_data;

    public Email(RequestEmail requestEmail){
        this.name = requestEmail.name();
        this.email = requestEmail.email();
        this.cupom = requestEmail.cupom();
        this.cupom_data = LocalDate.now();
        this.active = true;
    }

    public void setCupom_Data(LocalDate cupom_data) {
        this.cupom_data = cupom_data;
    }

    public LocalDate getCupom_Data() {
        return this.cupom_data;
    }
}
