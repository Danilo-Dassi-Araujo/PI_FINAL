package br.com.projetointegrador.store.dto.request.order;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CardPaymentRequestDTO {

    private String card_number;
    private String code;
    private String name;
    private LocalDate expire_date;
    private String portions;
}
