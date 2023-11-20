package br.com.projetointegrador.store.mock.models;

import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.util.ConstantMocks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.projetointegrador.store.util.ConstantMocks.*;

public class ProductMocks {

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
}
