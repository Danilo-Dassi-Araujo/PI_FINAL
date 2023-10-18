package br.com.projetointegrador.store.connector;

import br.com.projetointegrador.store.dto.response.CepFeignResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CepConnector {

    private final CepFeign cepFeign;

    public CepFeignResponseDTO cepFeign(String cep){
        return cepFeign.getCepInfo(cep);
    }
}