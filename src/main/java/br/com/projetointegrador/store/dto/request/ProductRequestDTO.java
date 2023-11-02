package br.com.projetointegrador.store.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String nameProduct;
    private String description;
    private Double rate;
    private BigDecimal priceProduct;
    private Integer stockQuantity;
    private List<ImageRequestDTO> imageList;
}