package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.CepDTORequest;
import br.com.projetointegrador.store.dto.response.*;
import br.com.projetointegrador.store.dto.response.product.ListingProductMainImageResponseDTO;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import br.com.projetointegrador.store.service.client.FindCepService;
import br.com.projetointegrador.store.service.product.ListingProductsService;
import br.com.projetointegrador.store.specification.FilterProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class GetController {

    private final ListingProductsService listingProductsService;
    private final FindCepService findCepService;
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @GetMapping("/listingProducts")
    public ResponseEntity<PageDTO<ListingProductMainImageResponseDTO>> listingProducts(FilterProducts controllerFilter, @RequestParam int page, @RequestParam int maxItems) {
        PageDTO<ListingProductMainImageResponseDTO> listingProductResponseDTOS = listingProductsService.listingProducts(controllerFilter, page, maxItems);
        return ResponseEntity.ok().body(listingProductResponseDTOS);
    }
    @GetMapping("/cep")
    public ResponseEntity<CepFeignResponseDTO> getCep(@RequestBody CepDTORequest cep) {
        CepFeignResponseDTO cepResponse = findCepService.findCep(cep.getCep());
        return ResponseEntity.ok().body(cepResponse);
    }

    @GetMapping("/listImagesById/{id}")
    public ResponseEntity<List<Image>> image(@PathVariable UUID id){
        Product byId = productRepository.findById(id).orElse(null);
        return ResponseEntity.ok().body(imageRepository.findAllByProductId(byId));
    }
}