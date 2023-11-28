package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CardPaymentResponseDTO {

    private UUID id;
    private UUID user_id;
    private String card_number;
    private String code;
    private String name;
    private String expire_date;
    private String portions;
    private String portion_value;
}