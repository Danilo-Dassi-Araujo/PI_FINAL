package br.com.projetointegrador.store.dto.request;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {

    private String email;
    private String nomeCompleto;
    private String password;
    private String passwordConfirmation;
    private String genero;
    private LocalDate dataNascimento;

}
