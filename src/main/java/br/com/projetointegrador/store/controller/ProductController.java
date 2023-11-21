package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.AlterStockRequestDTO;
import br.com.projetointegrador.store.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;
import br.com.projetointegrador.store.dto.response.product.ListingProductMainImageResponseDTO;
import br.com.projetointegrador.store.dto.response.product.ListingProductResponseDTO;
import br.com.projetointegrador.store.dto.response.PageDTO;
import br.com.projetointegrador.store.service.product.*;
import br.com.projetointegrador.store.specification.FilterProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    private final GetProduct getProduct;

    @PostMapping("/createProduct")
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequestDTO request) throws Exception {
        registerProductService.registerProduct(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editProduct/{id}")
    public ResponseEntity<Void> update(@RequestBody UpdateProductRequestDTO request,
                                       @PathVariable UUID id) throws Exception {
        request.setId(id);
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
    public PageDTO<ListingProductMainImageResponseDTO> listingProducts(FilterProducts filterProducts, int page, int maxItems) throws Exception {
        PageDTO<ListingProductMainImageResponseDTO> listingProductResponseDTOPageDTO = listingProductsService.listingProducts(filterProducts, page, maxItems);
        return ResponseEntity.ok().body(listingProductResponseDTOPageDTO).getBody();
    }

    @GetMapping("listingProduct/{id}")
    public ResponseEntity<ListingProductResponseDTO> listingProduct(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok().body(getProduct.getProduct(id));
    }

//    @PostMapping("/process-base64")
//    public ResponseEntity<String> processBase64(@RequestBody ImageRequestDTO base64String) {
//
//        imageSave.saveImage(base64String.getBase64());
//        return ResponseEntity.ok("Arquivo processado com sucesso.");
//    }

    @GetMapping("/getImage/{id}/{image}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String id, @PathVariable String image) {
        try {
            // Carregue a imagem do diretório especificado
            String imagePath = "produtosImagem/" + id + "/" + image;
            File file = new File(imagePath);
            byte[] imageBytes = Files.readAllBytes(file.toPath());

            // Configure os cabeçalhos da resposta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Defina o tipo de conteúdo da imagem

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}