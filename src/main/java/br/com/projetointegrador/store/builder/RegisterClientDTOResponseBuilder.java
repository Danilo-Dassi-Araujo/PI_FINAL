package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.request.ClientRequestDTO;
import br.com.projetointegrador.store.dto.response.RegisterClientDTOResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterClientDTOResponseBuilder {

    public static RegisterClientDTOResponse buildFrom(ClientRequestDTO clientRequestDTO){
        return RegisterClientDTOResponse
                .builder()
                .email(clientRequestDTO.getEmail())
                .nomeCompleto(clientRequestDTO.getFullname())
                .dataNascimento(clientRequestDTO.getBirth_date())
                .build();
    }
}