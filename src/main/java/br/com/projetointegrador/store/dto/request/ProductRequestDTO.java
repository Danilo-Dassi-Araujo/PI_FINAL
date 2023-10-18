package br.com.projetointegrador.store.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String nameProduct;
    private String description;
    private Double assessmentProduct;
    private BigDecimal priceProduct;
    private Integer stockQuantity;

}