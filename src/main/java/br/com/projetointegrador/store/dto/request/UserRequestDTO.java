package br.com.projetointegrador.store.dto.request;

import br.com.projetointegrador.store.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {


    private String email;
    private String name;
    private String password;
    private String passwordConfirmation;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private UUID loggedUserId;
}