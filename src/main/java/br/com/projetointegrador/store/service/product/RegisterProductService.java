package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.builder.ProductBuilder;
import br.com.projetointegrador.store.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import br.com.projetointegrador.store.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public void registerProduct(ProductRequestDTO productRequestDTO, List<MultipartFile> files) throws Exception {
        if (!ObjectUtils.isEmpty(productRequestDTO)) {
            ValidatorUtils.validateProduct(productRequestDTO);

            Product productToSave = ProductBuilder.buildFrom(productRequestDTO);
            Product savedProduct = productRepository.save(productToSave);
            if(!ObjectUtils.isEmpty(files)){
                for (MultipartFile file : files) {
                    Image imagem = new Image();
                    imagem.setNome(file.getOriginalFilename());
                    imagem.setDados(file.getBytes()); // Converta o MultipartFile para bytes
                    imagem.setProductId(savedProduct);
                    imageRepository.save(imagem);
                }
            }
        }
    }
}