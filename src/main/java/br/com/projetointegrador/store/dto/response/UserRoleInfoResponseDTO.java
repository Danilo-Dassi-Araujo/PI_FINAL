package br.com.projetointegrador.store.dto.response;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleInfoResponseDTO {

    private Integer id;
    private String name;
    private String nameEnum;
}
