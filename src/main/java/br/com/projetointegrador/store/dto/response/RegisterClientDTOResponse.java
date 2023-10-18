package br.com.projetointegrador.store.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientDTOResponse {

    private String email;
    private String nomeCompleto;
    private String genero;
    private LocalDate dataNascimento;

}