package br.com.projetointegrador.store.mock.models;

import br.com.projetointegrador.store.model.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static br.com.projetointegrador.store.mock.generators.ProductMockGenerator.generateProduct;
import static br.com.projetointegrador.store.util.ConstantMocks.INT;

public class ImageMock {

    public static List<br.com.projetointegrador.store.model.Image> getImage() {
        List<Image> imageList = new ArrayList<>();

        br.com.projetointegrador.store.model.Image image = br.com.projetointegrador.store.model.Image
                .builder()
                .id(INT)
                .productId(generateProduct())
                .isDefault(Boolean.TRUE)
                .path("TESTE")
                .build();
        imageList.add(image);
        br.com.projetointegrador.store.model.Image image2 = br.com.projetointegrador.store.model.Image
                .builder()
                .id(INT+1)
                .productId(generateProduct())
                .isDefault(Boolean.TRUE)
                .path("TESTE2")
                .build();
        imageList.add(image2);

        return imageList;

    }
}
