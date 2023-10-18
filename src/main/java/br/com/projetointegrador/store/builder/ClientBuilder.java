package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.model.Client;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class ClientBuilder {

    public static Client buildFrom(){
        return Client
                .builder()
                .email("")
                .nomeCompleto("")
                .dataNascimento(LocalDate.MAX)
                .genero("")
                .build();
    }
}
