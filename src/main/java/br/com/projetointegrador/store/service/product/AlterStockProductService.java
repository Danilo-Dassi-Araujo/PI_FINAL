package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.builder.ProductStockBuilder;
import br.com.projetointegrador.store.dto.request.AlterStockRequestDTO;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AlterStockProductService {

    private final ProductRepository productRepository;

    public void alterStock(AlterStockRequestDTO alterStockRequestDTO) throws Exception {
        if(ObjectUtils.isEmpty(alterStockRequestDTO.getId())){
            throw new Exception("O id está vazio");
        }

        if(alterStockRequestDTO.getQuantityStock() < 0){
            throw new Exception("A quantidade não pode ser menor que 0.");
        }

        Product product = productRepository.findById(alterStockRequestDTO.getId()).orElse(null);

        if(ObjectUtils.isEmpty(product)){
            throw new Exception("O produto não foi encontrado!");
        }

        product.setStockQuantity(alterStockRequestDTO.getQuantityStock());
        Product productToSave = ProductStockBuilder.buildFrom(product);
        productRepository.save(productToSave);
    }
}