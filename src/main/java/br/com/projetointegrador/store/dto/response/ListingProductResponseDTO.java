package br.com.projetointegrador.store.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListingProductResponseDTO {

    private String nameProduct;
    private UUID id;
    private Integer stockProduct;
    private BigDecimal priceProduct;
    private Boolean isActive;
}
