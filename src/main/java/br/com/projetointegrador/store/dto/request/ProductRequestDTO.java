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

    private String name;
    private String description;
    private Double rating;
    private BigDecimal price;
    private Integer stock;
    private List<ImageRequestDTO> images;
}