package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.dto.response.ImageListingResponseDTO;
import br.com.projetointegrador.store.dto.response.product.ListingProductResponseDTO;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetProduct {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public ListingProductResponseDTO getProduct(UUID id) throws Exception {
        Product product = productRepository.findById(id).orElse(null);

        if(ObjectUtils.isEmpty(product)){
            throw new Exception("Nenhum produto encontrado pelo id: " + id);
        }

        List<Image> allImagesForProduct = imageRepository.findAllByProductId(product);

        List<ImageListingResponseDTO> listImages = new ArrayList<>();
        for (Image i : allImagesForProduct) {
            ImageListingResponseDTO build =
                    ImageListingResponseDTO
                            .builder()
                            .id(i.getId())
                            .path(i.getPath())
                            .isDefault(i.getIsDefault())
                            .build();

            listImages.add(build);
        }

        return ListingProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .rating(product.getRate())
                .description(product.getDescription())
                .stock(product.getStockQuantity())
                .price(product.getPrice())
                .isActive(product.getIsActive())
                .newImages(new ArrayList<>())
                .imagesToDelete(new ArrayList<>())
                .images(listImages)
                .build();
    }
}
