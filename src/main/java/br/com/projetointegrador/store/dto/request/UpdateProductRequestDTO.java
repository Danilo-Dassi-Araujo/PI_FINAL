package br.com.projetointegrador.store.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestDTO {
    
    private UUID id;
    private String name;
    private String description;
    private Double rate;
    private BigDecimal price;
    private Integer stock;
    private String role;
    private List<UpdateProductImage> images;
    private List<UpdateProductImage> imagesToDelete;
    private List<UpdateProductImage> newImages;

}
