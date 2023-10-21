package br.com.projetointegrador.store.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlterStockRequestDTO {

    private UUID id;
    private Integer quantityStock;
}
