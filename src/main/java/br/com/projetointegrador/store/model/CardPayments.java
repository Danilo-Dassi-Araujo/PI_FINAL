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
@Table(name = "pagamentos_cartao", schema = "PUBLIC")
public class CardPayments {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "code")
    private String code;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "portions")
    private String portions;

    @Column(name = "portions_value")
    private String portionsValue;

    @PrePersist
    private void beforePersist() {
        this.id = UUID.randomUUID();
    }
}