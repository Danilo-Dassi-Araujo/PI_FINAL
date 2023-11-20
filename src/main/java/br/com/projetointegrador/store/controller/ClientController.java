package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.ClientRequestDTO;
import br.com.projetointegrador.store.service.client.RegisterClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final RegisterClientService registerClientService;

    @PostMapping
    public void createClient(@RequestBody ClientRequestDTO clientRequestDTO) throws Exception {
        registerClientService.registerClient(clientRequestDTO);
    }
}
