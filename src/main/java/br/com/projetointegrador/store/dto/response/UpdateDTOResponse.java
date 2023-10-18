package br.com.projetointegrador.store.dto.response;

import br.com.projetointegrador.store.enums.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDTOResponse {

    private String email;
    private String name;
    private String cpf;
    private UserRole role;
}
