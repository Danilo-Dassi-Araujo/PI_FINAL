package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageRepositoryService {

    public List<Image> imagesProduct(Product product, ImageRepository imageRepository){
        return getAllImagesForProduct(product, imageRepository);
    }

    private static List<Image> getAllImagesForProduct(Product product, ImageRepository imageRepository){
        return imageRepository.findAllByProductId(product);
    }
}
