package br.com.projetointegrador.store.dto.request.order;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderRequestDTO {
    private Double total_value;
    private UUID user_id;
    private Integer payment_method_id;
    private Integer shipping_id;
    private CardPaymentRequestDTO card_payment;
    private UUID delivery_address_id;
    private List<OrderProductRequestDTO> products;

}