package br.com.projetointegrador.store.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingsInfoResponseDTO {

    private Integer id;
    private String name;
    private String time;
    private Double price;

}
