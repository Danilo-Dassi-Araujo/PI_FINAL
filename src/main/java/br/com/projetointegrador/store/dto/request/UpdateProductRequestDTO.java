package br.com.projetointegrador.store.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private String nameProduct;
    private String description;
    private Double rate;
    private BigDecimal priceProduct;
    private Integer stockQuantity;
    private String role;
    private List<MultipartFile> files;

}
