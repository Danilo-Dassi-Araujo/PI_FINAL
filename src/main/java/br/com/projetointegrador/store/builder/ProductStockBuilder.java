package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.model.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductStockBuilder {

    public static Product buildFrom(Product product) {
        return Product
                .builder()
                .id(product.getId())
                .description(product.getDescription())
                .rate(product.getRate())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .isActive(product.getIsActive())
                .build();
    }
}

