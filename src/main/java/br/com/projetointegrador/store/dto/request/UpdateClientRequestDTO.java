package br.com.projetointegrador.store.dto.request;

import lombok.*;

import java.time.LocalDate;

@Setter
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class UpdateClientRequestDTO {

    private String name;
    private LocalDate birth_date;
    private String gender_id;
    private String password;
}
