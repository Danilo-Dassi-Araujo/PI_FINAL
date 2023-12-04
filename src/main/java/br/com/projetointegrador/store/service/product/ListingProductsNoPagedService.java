package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.dto.response.ImageListingResponseDTO;
import br.com.projetointegrador.store.dto.response.product.ListingProductMainImageResponseDTO;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingProductsNoPagedService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public List<ListingProductMainImageResponseDTO> listingAllProducts() throws Exception {
        List<ListingProductMainImageResponseDTO> response = new ArrayList<>();

        List<Product> allProducts = productRepository.findAll();

        for(Product product: allProducts){

            List<Image> allImagesForProduct = imageRepository.findAllByProductId(product);

            if(allImagesForProduct.isEmpty()){
                throw new Exception("Nenhuma imagem para este produto!");
            }
            ImageListingResponseDTO imageDefault = null;
            for(Image image: allImagesForProduct){
                if(Boolean.TRUE.equals(image.getIsDefault())){
                    imageDefault = ImageListingResponseDTO
                            .builder()
                            .id(image.getId())
                            .path(image.getPath())
                            .isDefault(image.getIsDefault())
                            .build();
                }
            }

            ListingProductMainImageResponseDTO itemResponse = ListingProductMainImageResponseDTO
                    .builder()
                    .id(product.getId())
                    .price(product.getPrice())
                    .stock(product.getStockQuantity())
                    .name(product.getName())
                    .images(imageDefault)
                    .description(product.getDescription())
                    .rating(product.getRate())
                    .isActive(product.getIsActive())
                    .build();

            response.add(itemResponse);
        }

        return response;
    }
}
