package br.com.projetointegrador.store.mock.models;


import br.com.projetointegrador.store.dto.request.UpdateProductImage;
import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;

import java.util.Collections;

import static br.com.projetointegrador.store.util.ConstantMocks.*;

public class UpdateProductRequestDTOMock {

    public static UpdateProductRequestDTO getUpdateProductRequestDTO() {
        return UpdateProductRequestDTO
                .builder()
                .name(TESTE)
                .price(BIG_DECIMAL)
                .rating(DOUBLE)
                .description(TESTE)
                .id(ID)
                .stock(INT)
                .build();

    }

    public static UpdateProductRequestDTO getUpdateProductRequestNewImagesDTO() {
        UpdateProductImage build = UpdateProductImage
                .builder()
                .id(INT)
                .path(TESTE)
                .isDefault(Boolean.TRUE)
                .build();


        return UpdateProductRequestDTO
                .builder()
                .name(TESTE)
                .price(BIG_DECIMAL)
                .rating(DOUBLE)
                .description(TESTE)
                .id(ID)
                .stock(INT)
                .newImages(Collections.singletonList(build))
                .build();

    }
    public static UpdateProductRequestDTO getUpdateProductRequestImagesDTO() {
        UpdateProductImage build = UpdateProductImage
                .builder()
                .id(INT)
                .path(TESTE)
                .isDefault(Boolean.TRUE)
                .build();


        return UpdateProductRequestDTO
                .builder()
                .name(TESTE)
                .price(BIG_DECIMAL)
                .rating(DOUBLE)
                .description(TESTE)
                .id(ID)
                .stock(INT)
                .images(Collections.singletonList(build))
                .build();

    }

    public static UpdateProductRequestDTO getUpdateProductRequestImagesDeleteDTO() {
        UpdateProductImage build = UpdateProductImage
                .builder()
                .id(INT)
                .path(TESTE)
                .isDefault(Boolean.TRUE)
                .build();


        return UpdateProductRequestDTO
                .builder()
                .name(TESTE)
                .price(BIG_DECIMAL)
                .rating(DOUBLE)
                .description(TESTE)
                .id(ID)
                .stock(INT)
                .imagesToDelete(Collections.singletonList(build))
                .build();

    }

}