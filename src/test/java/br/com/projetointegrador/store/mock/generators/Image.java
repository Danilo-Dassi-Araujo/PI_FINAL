package br.com.projetointegrador.store.mock.generators;

import java.util.List;

import static br.com.projetointegrador.store.mock.models.ImageMock.getImage;

public class Image {

    public static List<br.com.projetointegrador.store.model.Image> generateListImages(){
        return getImage();
    }
}
