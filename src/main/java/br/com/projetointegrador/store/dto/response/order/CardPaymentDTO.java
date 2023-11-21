package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CardPaymentDTO {

    private int id;
    private int user_id;
    private String card_number;
    private String code;
    private String name;
    private String expire_date;
    private String portions;
    private String portion_value;
}
