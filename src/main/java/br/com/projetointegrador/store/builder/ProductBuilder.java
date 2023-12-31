package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;
import br.com.projetointegrador.store.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
public class ProductBuilder {

    public static Product buildFrom(ProductRequestDTO productRequestDTO) {
        return Product
                .builder()
                .description(productRequestDTO.getDescription())
                .rate(productRequestDTO.getRating())
                .name(productRequestDTO.getName())
                .price(productRequestDTO.getPrice())
                .stockQuantity(productRequestDTO.getStock())
                .isActive(Boolean.TRUE)
                .build();
    }

    public static Product buildFrom(Product product) {
        return Product
                .builder()
                .id(product.getId())
                .description(product.getDescription())
                .rate(product.getRate())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .isActive(product.getIsActive() ? Boolean.FALSE : Boolean.TRUE)
                .createdAt(product.getCreatedAt())
                .build();
    }

    public static Product buildFrom(UpdateProductRequestDTO productRequest, Product product) {
        return Product
                .builder()
                .id(product.getId())
                .description(!ObjectUtils.isEmpty(productRequest.getDescription()) ? productRequest.getDescription() : product.getDescription())
                .rate(!ObjectUtils.isEmpty(productRequest.getRating()) ? productRequest.getRating() : product.getRate())
                .name(!ObjectUtils.isEmpty(productRequest.getName()) ? productRequest.getName() : product.getName())
                .price(!ObjectUtils.isEmpty(productRequest.getPrice()) ? productRequest.getPrice() : product.getPrice())
                .stockQuantity(!ObjectUtils.isEmpty(productRequest.getStock()) ? productRequest.getStock() : product.getStockQuantity())
                .isActive(Boolean.TRUE)
                .createdAt(product.getCreatedAt())
                .build();
    }


}