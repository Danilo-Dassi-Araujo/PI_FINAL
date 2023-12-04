package br.com.projetointegrador.store.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListingDTOResponse {

    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private String groupIndicator;
    private Boolean isActive;
}
