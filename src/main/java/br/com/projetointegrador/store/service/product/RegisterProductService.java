package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.builder.ProductBuilder;
import br.com.projetointegrador.store.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ProductRepository;
import br.com.projetointegrador.store.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class RegisterProductService {

    private final ProductRepository productRepository;

    public void registerProduct(ProductRequestDTO productRequestDTO) throws Exception {
        if (!ObjectUtils.isEmpty(productRequestDTO)) {
            ValidatorUtils.validateProduct(productRequestDTO);

            Product productToSave = ProductBuilder.buildFrom(productRequestDTO);
            productRepository.save(productToSave);
        }
    }
}