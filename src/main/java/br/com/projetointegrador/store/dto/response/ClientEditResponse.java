package br.com.projetointegrador.store.dto.response;


import br.com.projetointegrador.store.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientEditResponse {

    private UUID id;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String generoId;
    private List<Address> addresses;
}
