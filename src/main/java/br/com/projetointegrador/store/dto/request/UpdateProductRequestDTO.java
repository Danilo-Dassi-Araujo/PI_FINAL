package br.com.projetointegrador.store.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestDTO {
    
    private Integer id;
    private String nameProduct;
    private String description;
    private Double rate;
    private BigDecimal priceProduct;
    private Integer stockQuantity;

}
