package br.com.projetointegrador.store.mock.generators;

import static br.com.projetointegrador.store.mock.models.UpdateProductRequestDTOMock.*;

public class UpdateProductRequestDTO {

    public static br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO generateUpdateProductRequestDTO(){
        return getUpdateProductRequestDTO();
    }
    public static br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO generateUpdateProductRequestWithNewImagesDTO(){
        return getUpdateProductRequestNewImagesDTO();
    }
    public static br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO generateUpdateProductRequestWithImagesDTO(){
        return getUpdateProductRequestImagesDTO();
    }

    public static br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO generateUpdateProductRequestWithImagesToDeleteDTO(){
        return getUpdateProductRequestImagesDeleteDTO();
    }


}
