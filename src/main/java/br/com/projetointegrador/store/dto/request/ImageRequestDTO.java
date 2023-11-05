package br.com.projetointegrador.store.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestDTO {

    private String file;
    private Boolean isDefault;
}
