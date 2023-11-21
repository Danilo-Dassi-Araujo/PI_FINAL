package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeliveryAddressDTO {

    private int id;
    private int user_id;
    private String cep;
    private String uf;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    private int isDefault;
    private int isActive;
}
