package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DefaultImageDTO {

    private int id;
    private int product_id;
    private String path;
    private int isDefault;

}
