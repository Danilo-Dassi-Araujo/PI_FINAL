package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserOrderResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private int role_id;
    private int status;
}