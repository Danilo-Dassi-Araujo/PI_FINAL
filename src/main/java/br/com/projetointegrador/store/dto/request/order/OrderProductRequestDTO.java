package br.com.projetointegrador.store.dto.request.order;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderProductRequestDTO {

    private UUID product_id;
    private String quantity;
}
