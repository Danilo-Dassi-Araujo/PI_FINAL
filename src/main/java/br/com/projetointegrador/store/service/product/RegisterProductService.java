package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.builder.ProductBuilder;
import br.com.projetointegrador.store.dto.request.ImageRequestDTO;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ImageSave imageSave;

    public void registerProduct(ProductRequestDTO productRequestDTO) throws Exception {
        if (!ObjectUtils.isEmpty(productRequestDTO)) {
            ValidatorUtils.validateProduct(productRequestDTO);

            Product productToSave = ProductBuilder.buildFrom(productRequestDTO);
            Product savedProduct = productRepository.save(productToSave);
            if(!ObjectUtils.isEmpty(productRequestDTO.getImageList())){
                List<Image> listImage = new ArrayList<>();
                for (ImageRequestDTO image : productRequestDTO.getImageList()) {
                    Image imagem = new Image();
                    String path = imageSave.saveImage(image.getBase64(), savedProduct.getId().toString());
                    imagem.setProductId(savedProduct);
                    imagem.setPath(path);
                    imagem.setIsDefault(image.getIsDefault());
                    listImage.add(imagem);
                }
                imageRepository.saveAll(listImage);
            }
        }
    }
}