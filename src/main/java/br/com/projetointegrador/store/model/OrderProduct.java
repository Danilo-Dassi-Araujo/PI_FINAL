package br.com.projetointegrador.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos_produtos", schema = "PUBLIC")
public class OrderProduct {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    private void beforePersist() {
        this.id = UUID.randomUUID();
    }
}