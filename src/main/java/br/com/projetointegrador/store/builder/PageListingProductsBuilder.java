package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.response.ListingProductResponseDTO;
import br.com.projetointegrador.store.dto.response.PageDTO;
import br.com.projetointegrador.store.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageListingProductsBuilder {

    public static PageDTO<ListingProductResponseDTO> buildFrom(Page<Product> productPage) {
        return PageDTO.<ListingProductResponseDTO>builder()
                .pageNum(productPage.getNumber() + 1)
                .pageItemsNum(productPage.getNumberOfElements())
                .totalItems(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .data(getListingProductResponse(productPage.getContent()))
                .build();
    }

    private static List<ListingProductResponseDTO> getListingProductResponse(List<Product> productList) {
        return productList.stream()
                .filter(Objects::nonNull)
                .map(PageListingProductsBuilder::fromProduct)
                .collect(Collectors.toList());
    }

    private static ListingProductResponseDTO fromProduct(Product product) {
        return ListingProductResponseDTO.builder()
                .id(product.getId())
                .nameProduct(product.getName())
                .stockProduct(product.getStockQuantity())
                .priceProduct(product.getPrice())
                .isActive(product.getIsActive())
                .build();
    }
}
