package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditImage {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public void editImage(List<MultipartFile> files, UUID id) throws IOException {
    if(!ObjectUtils.isEmpty(files)){

        Product product = productRepository.findById(id).orElse(null);

        for (MultipartFile file : files) {
            Image imagem = new Image();
            imagem.setNome(file.getOriginalFilename());
            imagem.setDados(file.getBytes()); // Converta o MultipartFile para bytes
            imagem.setProductId(product);
            imageRepository.save(imagem);
        }
    }
    }
}
