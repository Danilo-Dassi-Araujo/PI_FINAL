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
public class OrderResponseDTO {

    private UUID id;
    private int user_id;
    private String order_code;
    private int delivery_address_id;
    private int status_id;
    private int payment_method_id;
    private int card_payment_id;
    private LocalDate date;
    private int shipping_id;
    private double value;

    private UserResponseDTO user;
    private DeliveryAddressDTO delivery_address;
    private StatusDTO status;
    private PaymentMethodDTO payment_method;
    private CardPaymentDTO card_payment;
    private ShippingDTO shipping;
    private List<OrderProductDTO> order_product;
}
