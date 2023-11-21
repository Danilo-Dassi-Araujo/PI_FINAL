package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderProductDTO {

    private int id;
    private int order_id;
    private int quantity;
    private int product_id;
    private ProductDTO product;
}
