package br.com.projetointegrador.store.controller.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponseDTO {

    private UUID id;

    private String name;

    private String email;

    private String role;

    private String cpf;

}
