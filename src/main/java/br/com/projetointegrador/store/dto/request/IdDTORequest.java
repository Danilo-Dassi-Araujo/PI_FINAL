package br.com.projetointegrador.store.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdDTORequest {

    private UUID id;

}
