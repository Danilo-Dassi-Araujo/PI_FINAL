package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.request.UserRequestDTO;
import br.com.projetointegrador.store.dto.response.RegisterDTOResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterDTOResponseBuilder {


    public static RegisterDTOResponse buildFrom(UserRequestDTO userRequestDTO){
        return RegisterDTOResponse
                .builder()
                .email(userRequestDTO.getEmail())
                .name(userRequestDTO.getName())
                .cpf(userRequestDTO.getCpf())
                .group(userRequestDTO.getRole())
                .build();
    }
}
