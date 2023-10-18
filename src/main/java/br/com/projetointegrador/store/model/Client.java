package br.com.projetointegrador.store.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CLIENTE", schema = "PUBLIC")
public class Client {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "data_de_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "genero")
    private String genero;

}
