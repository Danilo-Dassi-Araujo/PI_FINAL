package br.com.projetointegrador.store.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String nameProduct;
    private String description;
    private Double assessmentProduct;
    private Double priceProduct;
    private Integer stockQuantity;

}