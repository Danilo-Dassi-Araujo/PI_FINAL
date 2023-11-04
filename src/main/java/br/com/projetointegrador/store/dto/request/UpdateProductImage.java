package br.com.projetointegrador.store.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductImage {

        private Integer id;
        private String path;
        private Boolean isDefault;

}
