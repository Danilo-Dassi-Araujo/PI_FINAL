package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeliveryAddressResponseDTO {

    private UUID id;
    private UUID user_id;
    private String cep;
    private String uf;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    private Boolean isDefault;
    private Boolean isActive;

}
