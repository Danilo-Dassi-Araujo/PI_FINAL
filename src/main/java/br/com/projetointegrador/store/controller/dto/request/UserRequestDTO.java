package br.com.projetointegrador.store.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String cpf;

    @NotBlank
    private String password;

}
