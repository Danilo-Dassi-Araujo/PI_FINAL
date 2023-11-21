package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponseDTO {

    private int id;
    private String name;
    private String email;
    private String cpf;
    private int role_id;
    private int status;
}
