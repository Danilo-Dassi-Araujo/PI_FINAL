package br.com.projetointegrador.store.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListingProductResponseDTO {

    private String name;
    private String description;
    private double rating;
    private UUID id;
    private Integer stock;
    private BigDecimal price;
    private Boolean isActive;
    private List<ImageListingResponseDTO>newImages;
    private List<ImageListingResponseDTO> imagesToDelete;
    private List<ImageListingResponseDTO>images;
}
