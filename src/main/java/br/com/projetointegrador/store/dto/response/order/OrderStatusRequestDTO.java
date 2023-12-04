package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusRequestDTO {

    private Integer status_id;
}
