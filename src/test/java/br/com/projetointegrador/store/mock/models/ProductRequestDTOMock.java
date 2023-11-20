package br.com.projetointegrador.store.mock.models;

import br.com.projetointegrador.store.dto.request.ProductRequestDTO;

import static br.com.projetointegrador.store.util.ConstantMocks.*;

public class ProductRequestDTOMock {

    public static ProductRequestDTO getMock(){
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
}
