package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AllMyOrdersResponseDTO {

    private UserOrderResponseDTO user;
    private DeliveryAddressResponseDTO delivery_address;
    private StatusDTO status;
    private CardPaymentResponseDTO card_payment;
    private PaymentMethodDTO payment_method;
    private List<OrderProductResponseDTO> order_product;
    private double value;
    private UUID id;
    private UUID user_id;
    private String order_code;
    private UUID delivery_address_id;
    private int status_id;
    private int payment_method_id;
    private UUID card_payment_id;
    private int shipping_id;
    private LocalDate date;
}
