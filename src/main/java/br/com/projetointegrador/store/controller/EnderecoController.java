package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.CepDTORequest;
import br.com.projetointegrador.store.dto.request.DeliveryAddressRequestDTO;
import br.com.projetointegrador.store.dto.response.CepFeignResponseDTO;
import br.com.projetointegrador.store.service.client.ActiveAndInactiveAddressService;
import br.com.projetointegrador.store.service.client.FindCepService;
import br.com.projetointegrador.store.service.client.RegisterNewAddressDeliveryService;
import br.com.projetointegrador.store.service.client.UpdateDefaultAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {

    private final FindCepService findCepService;
    private final RegisterNewAddressDeliveryService registerNewAddressDeliveryService;
    private final UpdateDefaultAddressService updateDefaultAddressService;
    private final ActiveAndInactiveAddressService activeAndInactiveAddressService;

    @GetMapping("/cep")
    public ResponseEntity<CepFeignResponseDTO> findCep(@RequestBody CepDTORequest cepDTORequest) {
        CepFeignResponseDTO cep = findCepService.findCep(cepDTORequest.getCep());

        return ResponseEntity.ok().body(cep);
    }

    @PutMapping("/newDeliveryAddress/{id}")
    public void addAddress(@PathVariable UUID id, @RequestBody DeliveryAddressRequestDTO deliveryToAdd) {
        registerNewAddressDeliveryService.registerNewAddress(deliveryToAdd, id);
    }

    @PutMapping("/newDefaultAddress/{id}/{idAddress}")
    public void newDefaultAddress(@PathVariable UUID id, @PathVariable UUID idAddress) throws Exception {
        updateDefaultAddressService.updateDefaultAddress(id, idAddress);
    }

    @PutMapping("/changeDeliveryAddressStatus/{id}")
    public void activeOrInactiveAddress(@PathVariable UUID id) throws Exception {
        activeAndInactiveAddressService.activeOrInactiveAddress(id);
    }
}