package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.AlterStockRequestDTO;
import br.com.projetointegrador.store.dto.request.ImageRequestDTO;
import br.com.projetointegrador.store.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;
import br.com.projetointegrador.store.dto.response.ListingProductResponseDTO;
import br.com.projetointegrador.store.dto.response.PageDTO;
import br.com.projetointegrador.store.service.product.*;
import br.com.projetointegrador.store.specification.FilterProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ListingProductsService listingProductsService;
    private final UpdateProductService updateProductService;
    private final AlterStockProductService alterStockProductService;
    private final InactiveAndActiveProductService inactiveAndActiveProductService;
    private final RegisterProductService registerProductService;
    private final ImageSave imageSave;


    @PostMapping("/createProduct")
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequestDTO request) throws Exception {
        registerProductService.registerProduct(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editProduct")
    public ResponseEntity<Void> update(@RequestBody UpdateProductRequestDTO request) throws Exception {
        updateProductService.updateProduct(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/alterStock")
    public ResponseEntity<Void> alterStock(@RequestBody AlterStockRequestDTO request) throws Exception {
        alterStockProductService.alterStock(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/toggleAvailable/{id}")
    public ResponseEntity<Void> toggleAvailable(@PathVariable UUID id) throws Exception {
        inactiveAndActiveProductService.activeAndInactiveProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listingProducts")
    public PageDTO<ListingProductResponseDTO> listingProducts(FilterProducts filterProducts, int page, int maxItems) throws Exception {
        PageDTO<ListingProductResponseDTO> listingProductResponseDTOPageDTO = listingProductsService.listingProducts(filterProducts, page, maxItems);
        return ResponseEntity.ok().body(listingProductResponseDTOPageDTO).getBody();
    }

//    @PostMapping("/process-base64")
//    public ResponseEntity<String> processBase64(@RequestBody ImageRequestDTO base64String) {
//
//        imageSave.saveImage(base64String.getBase64());
//        return ResponseEntity.ok("Arquivo processado com sucesso.");
//    }
}