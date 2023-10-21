package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.CepDTORequest;
import br.com.projetointegrador.store.dto.response.CepFeignResponseDTO;
import br.com.projetointegrador.store.service.client.FindCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {

    private final FindCepService findCepService;

    @GetMapping("/cep")
    public ResponseEntity<CepFeignResponseDTO> findCep(@RequestBody CepDTORequest cepDTORequest){
        CepFeignResponseDTO cep = findCepService.findCep(cepDTORequest.getCep());

        return ResponseEntity.ok().body(cep);
    }
}
