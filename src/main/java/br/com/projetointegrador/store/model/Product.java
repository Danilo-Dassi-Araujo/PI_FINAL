package br.com.projetointegrador.store.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "rate")
    private double rate;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void beforePersist() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    private void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
