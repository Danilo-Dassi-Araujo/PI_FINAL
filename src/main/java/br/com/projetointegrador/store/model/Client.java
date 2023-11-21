package br.com.projetointegrador.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes", schema = "PUBLIC")
public class Client {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String nomeCompleto;

    @Column(name = "password")
    private String senha;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "birth_date")
    private LocalDate dataNascimento;

    @Column(name = "gender")
    private String genero;

    @PrePersist
    private void beforePersist() {
        this.id = UUID.randomUUID();
    }

}
