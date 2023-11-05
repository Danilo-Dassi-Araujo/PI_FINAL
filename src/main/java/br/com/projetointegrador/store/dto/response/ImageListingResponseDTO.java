package br.com.projetointegrador.store.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageListingResponseDTO {

    private Integer id;
    private String path;
    private Boolean isDefault;
}
