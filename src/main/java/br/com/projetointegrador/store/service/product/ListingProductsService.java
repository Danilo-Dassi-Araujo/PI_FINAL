package br.com.projetointegrador.store.service.product;

import br.com.projetointegrador.store.builder.PageListingProductsBuilder;
import br.com.projetointegrador.store.dto.response.ImageListingResponseDTO;
import br.com.projetointegrador.store.dto.response.ListingProductMainImageResponseDTO;
import br.com.projetointegrador.store.dto.response.ListingProductResponseDTO;
import br.com.projetointegrador.store.dto.response.PageDTO;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.specification.ProductSpecification;
import br.com.projetointegrador.store.specification.FilterProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ListingProductsService {

    private final ProductSpecification productSpecification;
    private final ImageRepository imageRepository;
    private final PageListingProductsBuilder pageListingProductsBuilder;

    public PageDTO<ListingProductMainImageResponseDTO> listingProducts(FilterProducts controllerFilter, int page, int maxItems) {
        Pageable pageable = PageRequest.of(page - 1, maxItems);
        Page<Product> pageProduct = productSpecification.findBy(controllerFilter, pageable);

        return pageListingProductsBuilder.buildFrom(pageProduct);
    }
}