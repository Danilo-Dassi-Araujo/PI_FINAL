package br.com.projetointegrador.store.dto.response.order;

import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.model.CardPayments;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.model.Product;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponseCodeDTO {

    private UUID id;
    private Client cliente;
    private String orderCode;
    private Address deliveryAddress;
    private Integer statusId;
    private String status;
    private Integer paymentMethodId;
    private CardPayments cardPayment;
    private LocalDate date;
    private Integer shippingId;
    private Double value;
    private Double price;
    private PaymentMethodDTO paymentMethod;
    private List<OrderProductDTO> products;

}
