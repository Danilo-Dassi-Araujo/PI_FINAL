package br.com.projetointegrador.store.mock.generators;

import br.com.projetointegrador.store.dto.request.ProductRequestDTO;

import static br.com.projetointegrador.store.mock.models.ProductRequestDTOMock.getProductImageRequestDTO;
import static br.com.projetointegrador.store.mock.models.ProductRequestDTOMock.getProductRequestDTO;

public class ProductRequestDTOGenerator {

    public static ProductRequestDTO generateProductRequestDTO(){
        return getProductRequestDTO();
    }

    public static ProductRequestDTO generateProductImageRequestDTO(){
        return getProductImageRequestDTO();
    }

}
