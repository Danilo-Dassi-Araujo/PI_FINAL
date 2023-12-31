package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ShippingDTO {

    private int id;
    private String name;
    private double price;
    private String time;

}
