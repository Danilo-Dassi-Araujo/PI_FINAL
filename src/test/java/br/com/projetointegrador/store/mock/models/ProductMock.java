package br.com.projetointegrador.store.mock.models;

import br.com.projetointegrador.store.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.projetointegrador.store.util.ConstantMocks.*;

public class ProductMock {

    public static Product getProduct() {
        return Product
                .builder()
                .id(ID)
                .createdAt(LocalDate.now())
                .price(BIG_DECIMAL)
                .name(TESTE)
                .rate(DOUBLE)
                .description(TESTE)
                .isActive(Boolean.TRUE)
                .stockQuantity(INT)
                .updatedAt(LocalDateTime.MAX)
                .build();
    }

    public static Optional<Product> getProductOptional() {
        return Optional.ofNullable(Product
                .builder()
                .id(ID)
                .createdAt(LocalDate.now())
                .price(BIG_DECIMAL)
                .name(TESTE)
                .rate(DOUBLE)
                .description(TESTE)
                .isActive(Boolean.TRUE)
                .stockQuantity(INT)
                .updatedAt(LocalDateTime.MAX)
                .build());
    }
}
