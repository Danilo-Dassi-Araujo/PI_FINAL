package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.ClientRequestDTO;
import br.com.projetointegrador.store.dto.request.UpdateClientRequestDTO;
import br.com.projetointegrador.store.service.client.RegisterClientService;
import br.com.projetointegrador.store.service.client.UpdateClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final RegisterClientService registerClientService;
    private final UpdateClientService updateClientService;

    @PostMapping("/register")
    public void createClient(@RequestBody ClientRequestDTO clientRequestDTO) throws Exception {
        registerClientService.registerClient(clientRequestDTO);
    }

    @PutMapping("/update/{id}")
    public void updateClient(@PathVariable UUID id, @RequestBody UpdateClientRequestDTO clientToSend) throws Exception {
        updateClientService.updateClient(id, clientToSend);
    }
}
