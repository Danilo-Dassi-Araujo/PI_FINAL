package br.com.projetointegrador.store.mock.models;

import br.com.projetointegrador.store.dto.request.ImageRequestDTO;
import br.com.projetointegrador.store.dto.request.ProductRequestDTO;

import java.util.Collections;

import static br.com.projetointegrador.store.util.ConstantMocks.*;

public class ProductRequestDTOMock {

    public static ProductRequestDTO getProductRequestDTO(){
        return ProductRequestDTO
                .builder()
                .name(TESTE)
                .price(BIG_DECIMAL)
                .stock(INT)
                .description(TESTE)
                .rating(DOUBLE)
//                .images()
                .build();
    }

    public static ProductRequestDTO getProductImageRequestDTO(){
        ImageRequestDTO build = ImageRequestDTO
                .builder()
                .file("IMAGEM")
                .isDefault(Boolean.TRUE)
                .build();

        return ProductRequestDTO
                .builder()
                .name(TESTE)
                .price(BIG_DECIMAL)
                .stock(INT)
                .description(TESTE)
                .rating(DOUBLE)
                .images(Collections.singletonList(build))
                .build();
    }
}
