package br.com.projetointegrador.store.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressRequestDTO {

    private String cep;
    private String city;
    private String complement;
    private String neighborhood;
    private String number;
    private String street;
    private String uf;
    private Boolean isDefault;
}
