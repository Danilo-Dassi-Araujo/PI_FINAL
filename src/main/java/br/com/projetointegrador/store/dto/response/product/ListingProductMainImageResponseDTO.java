package br.com.projetointegrador.store.dto.response.product;

import br.com.projetointegrador.store.dto.response.ImageListingResponseDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListingProductMainImageResponseDTO {

    private String name;
    private String description;
    private double rating;
    private UUID id;
    private Integer stock;
    private BigDecimal price;
    private Boolean isActive;
    private ImageListingResponseDTO images;
}
