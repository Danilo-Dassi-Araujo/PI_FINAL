package br.com.projetointegrador.store.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListingDTOResponse {

    private String name;
    private String email;
    private String cpf;
    private String groupIndicator;
    private Boolean isActive;
}
