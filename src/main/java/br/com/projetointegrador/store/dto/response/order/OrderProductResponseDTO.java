package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderProductResponseDTO {

        private UUID id;
        private UUID order_id;
        private int quantity;
        private UUID product_id;
        private ProductResponseDTO product;
}
