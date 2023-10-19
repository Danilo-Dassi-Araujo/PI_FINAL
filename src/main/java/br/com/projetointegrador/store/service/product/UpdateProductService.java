package br.com.projetointegrador.store.service.product;


import br.com.projetointegrador.store.builder.ProductBuilder;
import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;
import br.com.projetointegrador.store.enums.UserRole;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class UpdateProductService {

    private final ProductRepository productRepository;

    public void updateProduct(UpdateProductRequestDTO updateProductRequestDTO, String role) throws Exception {
        if (ObjectUtils.isEmpty(updateProductRequestDTO)) {
            throw new Exception("Request vazia!");
        }

        if (!UserRole.ADMIN.getName().equals(role)) {
            throw new Exception("Somente administradores podem alterar os produtos!");
        }

        Product product = productRepository.findById(updateProductRequestDTO.getId()).orElse(null);

        if(ObjectUtils.isEmpty(product)){
            throw new Exception("Nenhum produto encontrado pelo id: " + updateProductRequestDTO.getId());
        }

        Product updateProductToSave = ProductBuilder.buildFrom(updateProductRequestDTO, product);

        productRepository.save(updateProductToSave);

    }
}