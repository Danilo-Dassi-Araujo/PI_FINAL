package br.com.projetointegrador.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders", schema = "PUBLIC")
public class Order {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client cliente;

    @Column(name = "order_code")
    private String orderCode;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "payment_method_id")
    private Integer paymentMethodId;

    @ManyToOne
    @JoinColumn(name = "card_payment_id")
    private CardPayments cardPayment;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "shipping_id")
    private Integer shippingId;

    @Column(name = "value")
    private Double value;

    @PrePersist
    private void beforePersist() {
        this.id = UUID.randomUUID();
    }
}