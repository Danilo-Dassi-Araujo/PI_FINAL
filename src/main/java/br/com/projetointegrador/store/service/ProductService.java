package br.com.projetointegrador.store.service;


import br.com.projetointegrador.store.controller.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.controller.dto.response.ProductResponseDTO;
import br.com.projetointegrador.store.exceptions.ProductNotFoundException;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ProductResponseDTO findById(UUID id) {
        var product = this.getById(id);

        return this.modelMapper.map(product, ProductResponseDTO.class);
    }

    public ProductResponseDTO save(ProductRequestDTO request) {
        var product = this.modelMapper.map(request, Product.class);
        var updatedProduct = this.productRepository.save(product);

        return this.modelMapper.map(updatedProduct, ProductResponseDTO.class);
    }

    public ProductResponseDTO update(UUID id, ProductRequestDTO request) {
        var product = this.getById(id);

        BeanUtils.copyProperties(request, product);

        this.productRepository.save(product);

        return this.modelMapper.map(product, ProductResponseDTO.class);
    }

    public List<ProductResponseDTO> findAll() {
        return this.productRepository.findAll().stream().map(product -> this.modelMapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }

    public void deleteById(UUID id) {
        var product = this.getById(id);

        this.productRepository.delete(product);
    }

    private Product getById(UUID id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
    }

}
