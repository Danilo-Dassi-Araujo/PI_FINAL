package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.response.ImageListingResponseDTO;
import br.com.projetointegrador.store.dto.response.product.ListingProductMainImageResponseDTO;
import br.com.projetointegrador.store.dto.response.PageDTO;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageListingProductsBuilder {

    private final ImageRepository imageRepository;


    public PageDTO<ListingProductMainImageResponseDTO> buildFrom(Page<Product> productPage) {
        return PageDTO.<ListingProductMainImageResponseDTO>builder()
                .pageNum(productPage.getNumber() + 1)
                .pageItemsNum(productPage.getNumberOfElements())
                .totalItems(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .data(getListingProductResponse(productPage.getContent()))
                .build();
    }

    private List<ListingProductMainImageResponseDTO> getListingProductResponse(List<Product> productList) {
        return productList.stream()
                .filter(Objects::nonNull)
                .map(this::fromProduct)
                .collect(Collectors.toList());
    }

    private ListingProductMainImageResponseDTO fromProduct(Product product) {

        List<Image> allImagesForProduct = imageRepository.findAllByProductId(product);

        List<Image> imageDefault = allImagesForProduct.stream()
                .filter(image -> Boolean.TRUE.equals(image.getIsDefault()))
                .toList();

        ImageListingResponseDTO build =
                ImageListingResponseDTO
                        .builder()
                        .id(imageDefault.get(0).getId())
                        .path(imageDefault.get(0).getPath())
                        .isDefault(imageDefault.get(0).getIsDefault())
                        .build();

        return ListingProductMainImageResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .rating(product.getRate())
                .description(product.getDescription())
                .stock(product.getStockQuantity())
                .price(product.getPrice())
                .isActive(product.getIsActive())
                .images(build)
                .build();
    }
}
