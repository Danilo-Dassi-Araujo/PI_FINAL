package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.controller.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.controller.dto.response.ProductResponseDTO;
import br.com.projetointegrador.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO save(@RequestBody ProductRequestDTO request) {
        return this.productService.save(request);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO findById(@PathVariable UUID id) {
        return this.productService.findById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable UUID id, @RequestBody ProductRequestDTO request) {
        return this.productService.update(id, request);
    }

    @GetMapping
    public List<ProductResponseDTO> findAll() {
        return this.productService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        this.productService.deleteById(id);
    }

}
