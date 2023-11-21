package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.request.ClientRequestDTO;
import br.com.projetointegrador.store.model.Client;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientBuilder {

    public static Client buildFrom(ClientRequestDTO clientRequestDTO, String password, String gender) {

        String cpfClear = clientRequestDTO.getCpf().replace("-", "");
        String cpf = cpfClear.replace(".", "");

        return Client
                .builder()
                .email(clientRequestDTO.getEmail())
                .nomeCompleto(clientRequestDTO.getFullname())
                .cpf(cpf)
                .dataNascimento(clientRequestDTO.getBirth_date())
                .senha(password)
                .genero(gender)
                .build();
    }
}