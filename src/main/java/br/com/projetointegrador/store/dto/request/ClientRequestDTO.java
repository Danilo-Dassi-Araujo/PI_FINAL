package br.com.projetointegrador.store.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {

    private String email;
    private String fullname;
    private String password;
    private String cpf;
    private LocalDate birth_date;
    private String gender_id;
    private DeliveryAddressRequestDTO billing_address;
    private List<DeliveryAddressRequestDTO> delivery_address;

}
