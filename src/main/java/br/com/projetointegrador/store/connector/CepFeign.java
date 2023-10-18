package br.com.projetointegrador.store.connector;

import br.com.projetointegrador.store.dto.response.CepFeignResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface CepFeign {

    @GetMapping("/{cep}/json/")
    CepFeignResponseDTO getCepInfo(@PathVariable("cep") String cep);
}
