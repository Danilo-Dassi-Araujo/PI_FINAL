package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.response.UpdateDTOResponse;
import br.com.projetointegrador.store.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateDTOResponseBuilder {

    public static UpdateDTOResponse buildFrom(User user){
        return UpdateDTOResponse
                .builder()
                .email(user.getEmail())
                .name(user.getName())
                .cpf(user.getCpf())
                .role(user.getRole())
                .build();
    }
}