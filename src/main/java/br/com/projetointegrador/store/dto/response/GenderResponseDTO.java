package br.com.projetointegrador.store.dto.response;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenderResponseDTO {

    private String id;
    private String gender;
}
