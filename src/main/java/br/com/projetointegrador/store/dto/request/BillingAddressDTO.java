package br.com.projetointegrador.store.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingAddressDTO {

    private String cep;
    private String city;
    private String complement;
    private String neighborhood;
    private String number;
    private String street;
    private String uf;
}
