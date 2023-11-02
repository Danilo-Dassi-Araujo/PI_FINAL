package br.com.projetointegrador.store.service.product;


import br.com.projetointegrador.store.builder.ProductBuilder;
import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;
import br.com.projetointegrador.store.enums.UserRole;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;


    public void updateProduct(UpdateProductRequestDTO updateProductRequestDTO) throws Exception {
        if (ObjectUtils.isEmpty(updateProductRequestDTO)) {
            throw new Exception("Request vazia!");
        }

        if (!UserRole.ADMIN.getName().equals(updateProductRequestDTO.getRole())) {
            throw new Exception("Somente administradores podem alterar os produtos!");
        }

        Product product = productRepository.findById(updateProductRequestDTO.getId()).orElse(null);

        if(ObjectUtils.isEmpty(product)){
            throw new Exception("Nenhum produto encontrado pelo id: " + updateProductRequestDTO.getId());
        }

        Product updateProductToSave = ProductBuilder.buildFrom(updateProductRequestDTO, product);

        Product productToSave = productRepository.save(updateProductToSave);

        if (!ObjectUtils.isEmpty(updateProductRequestDTO)) {
            List<Image> imagens = new ArrayList<>();
            for (MultipartFile file : updateProductRequestDTO.getFiles()) {
                Image imagem = new Image();
                imagem.setDados(file.getBytes().clone()); // Converta o MultipartFile para bytes
                imagem.setNome(file.getOriginalFilename());
                imagem.setProductId(productToSave);

                imagens.add(imagem);
            }
            imageRepository.saveAll(imagens);
        }
    }
}