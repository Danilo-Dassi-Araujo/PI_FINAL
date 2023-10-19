package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.CepDTORequest;
import br.com.projetointegrador.store.dto.response.CepFeignResponseDTO;
import br.com.projetointegrador.store.dto.response.ListingDTOResponse;
import br.com.projetointegrador.store.dto.response.ListingProductResponseDTO;
import br.com.projetointegrador.store.dto.response.PageDTO;
import br.com.projetointegrador.store.service.client.FindCepService;
import br.com.projetointegrador.store.service.product.ListingProductsService;
import br.com.projetointegrador.store.service.user.ListingUsersService;
import br.com.projetointegrador.store.specification.ControllerFilter;
import br.com.projetointegrador.store.specification.FilterProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class GetController {

    private final ListingProductsService listingProductsService;
    private final FindCepService findCepService;



    @GetMapping("/listingProducts")
    public ResponseEntity<PageDTO<ListingProductResponseDTO>> listingProducts(FilterProducts controllerFilter, @RequestParam int page, @RequestParam int maxItems) {
        PageDTO<ListingProductResponseDTO> listingProductResponseDTOS = listingProductsService.listingProducts(controllerFilter, page, maxItems);
        return ResponseEntity.ok().body(listingProductResponseDTOS);
    }

    @GetMapping("/cep")
    public ResponseEntity<CepFeignResponseDTO> getCep(@RequestBody CepDTORequest cep) {
        CepFeignResponseDTO cepResponse = findCepService.findCep(cep.getCep());
        return ResponseEntity.ok().body(cepResponse);
    }
}