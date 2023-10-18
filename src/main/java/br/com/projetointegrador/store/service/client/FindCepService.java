package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.connector.CepConnector;
import br.com.projetointegrador.store.dto.response.CepFeignResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindCepService {

    private final CepConnector cepConnector;

    public CepFeignResponseDTO findCep(String cep){
        log.debug("validando cep: {}", cep);
        return cepConnector.cepFeign(cep);
    }
}