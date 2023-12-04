package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {

    private UUID id;
    private String name;
    private double rating;
    private String description;
    private BigDecimal price;
    private String stock;
    private Boolean isActive;
    private DefaultImageDTO default_image;

}
