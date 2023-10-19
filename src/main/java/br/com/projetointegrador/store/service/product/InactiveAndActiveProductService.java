package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.builder.ProductBuilder;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class InactiveAndActiveProductService {

    private final ProductRepository productRepository;

    public void activeAndInactiveProduct(UUID id) throws Exception {

        if (!ObjectUtils.isEmpty(id)) {
            Product productById = productRepository.findById(id).orElse(null);

            if (ObjectUtils.isEmpty(productById)) {
                throw new Exception("Nenhum produto encontrado para id: " + id);
            }
            Product productToSave = ProductBuilder.buildFrom(productById);

            productRepository.save(productToSave);
        } else {
            throw new Exception("Id está vazio!");
        }
    }

}
