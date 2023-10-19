package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.builder.ProductStockBuilder;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlterStockProductService {

    private final ProductRepository productRepository;

    public void alterStock(UUID id, Integer newProductStock) throws Exception {
        if(ObjectUtils.isEmpty(id)){
            throw new Exception("O id está vazio");
        }

        if(newProductStock < 0){
            throw new Exception("A quantidade não pode ser menor que 0.");
        }

        Product product = productRepository.findById(id).orElse(null);

        if(ObjectUtils.isEmpty(product)){
            throw new Exception("O produto não foi encontrado!");
        }

        product.setStockQuantity(newProductStock);
        Product productToSave = ProductStockBuilder.buildFrom(product);
        productRepository.save(productToSave);
    }
}