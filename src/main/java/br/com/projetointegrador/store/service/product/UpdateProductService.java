package br.com.projetointegrador.store.service.product;


import br.com.projetointegrador.store.builder.ProductBuilder;
import br.com.projetointegrador.store.dto.request.UpdateProductImage;
import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;
import br.com.projetointegrador.store.enums.UserRole;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UpdateProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ImageSave imageSave;

    public void updateProduct(UpdateProductRequestDTO updateProductRequestDTO) throws Exception {
        if (ObjectUtils.isEmpty(updateProductRequestDTO)) {
            throw new Exception("Request vazia!");
        }

        if (!UserRole.ADMIN.getName().equals(updateProductRequestDTO.getRole())) {
            throw new Exception("Somente administradores podem alterar os produtos!");
        }

        Product product = productRepository.findById(updateProductRequestDTO.getId()).orElse(null);

        if (ObjectUtils.isEmpty(product)) {
            throw new Exception("Nenhum produto encontrado pelo id: " + updateProductRequestDTO.getId());
        }

        Product updateProductToSave = ProductBuilder.buildFrom(updateProductRequestDTO, product);

        Product productToSave = productRepository.save(updateProductToSave);

        if (!ObjectUtils.isEmpty(updateProductRequestDTO.getImagesToSave())) {
            imagesToSave(updateProductRequestDTO, productToSave);
        }

        if (!ObjectUtils.isEmpty(updateProductRequestDTO.getImagesRest())) {
            imagesRest(updateProductRequestDTO, productToSave);
        }

        if (!ObjectUtils.isEmpty(updateProductRequestDTO.getImagesToDelete())) {
            imagesToDelete(updateProductRequestDTO, productToSave);
        }
    }

    private void imagesToSave(UpdateProductRequestDTO updateProductRequestDTO, Product productToSave) {

        List<UpdateProductImage> imagesIsDefaultTrue = new ArrayList<>();
        for (UpdateProductImage image : updateProductRequestDTO.getImagesToSave()) {
            if (Boolean.TRUE.equals(image.getIsDefault())) {
                imagesIsDefaultTrue.add(image);
            }
        }

        if (!imagesIsDefaultTrue.isEmpty()) {
            List<Image> allByProductId = imageRepository.findAllByProductId(productToSave);
            allByProductId
                    .forEach(image -> image.setIsDefault(false));

            for (UpdateProductImage image : updateProductRequestDTO.getImagesToSave()) {
                String path = imageSave.saveImage(image.getPath(), productToSave.getId().toString());

                Image imagem = Image
                        .builder()
                        .path(path)
                        .isDefault(image.getIsDefault())
                        .productId(productToSave)
                        .build();

                if (!ObjectUtils.isEmpty(image.getId())) {
                    imagem.setId(imagem.getId());
                }

                allByProductId.add(imagem);
            }
            imageRepository.saveAll(allByProductId);
        }

        if (imagesIsDefaultTrue.isEmpty()) {

            List<Image> listImage = new ArrayList<>();

            for (UpdateProductImage image : updateProductRequestDTO.getImagesToSave()) {
                String path = imageSave.saveImage(image.getPath(), productToSave.getId().toString());

                Image imagem = Image
                        .builder()
                        .path(path)
                        .isDefault(image.getIsDefault())
                        .productId(productToSave)
                        .build();

                if (!ObjectUtils.isEmpty(image.getId())) {
                    imagem.setId(imagem.getId());
                }
                listImage.add(imagem);
            }
            imageRepository.saveAll(listImage);
        }
    }

    private void imagesRest(UpdateProductRequestDTO updateProductRequestDTO, Product productToSave) {

        List<UpdateProductImage> imagesIsDefaultTrue = new ArrayList<>();
        for (UpdateProductImage image : updateProductRequestDTO.getImagesRest()) {
            if (Boolean.TRUE.equals(image.getIsDefault())) {
                imagesIsDefaultTrue.add(image);
            }
        }

        if (!imagesIsDefaultTrue.isEmpty()) {
            List<Image> allByProductId = imageRepository.findAllByProductId(productToSave);
            allByProductId
                    .forEach(image -> image.setIsDefault(false));

            for (UpdateProductImage image : updateProductRequestDTO.getImagesRest()) {
                String path = imageSave.saveImage(image.getPath(), productToSave.getId().toString());

                Image imagem = Image
                        .builder()
                        .path(path)
                        .isDefault(image.getIsDefault())
                        .productId(productToSave)
                        .build();

                if (!ObjectUtils.isEmpty(image.getId())) {
                    imagem.setId(imagem.getId());
                }

                allByProductId.add(imagem);
            }
            imageRepository.saveAll(allByProductId);
        }

        if (imagesIsDefaultTrue.isEmpty()) {

            List<Image> listImage = new ArrayList<>();

            for (UpdateProductImage image : updateProductRequestDTO.getImagesRest()) {
                String path = imageSave.saveImage(image.getPath(), productToSave.getId().toString());

                Image imagem = Image
                        .builder()
                        .path(path)
                        .isDefault(image.getIsDefault())
                        .productId(productToSave)
                        .build();

                if (!ObjectUtils.isEmpty(image.getId())) {
                    imagem.setId(imagem.getId());
                }
                listImage.add(imagem);
            }
            imageRepository.saveAll(listImage);
        }
    }

    private void imagesToDelete(UpdateProductRequestDTO updateProductRequestDTO, Product productToSave) {
        List<Integer> list = updateProductRequestDTO.getImagesToDelete().stream().map(UpdateProductImage::getId).toList();
        List<Image> allByProductId = imageRepository.findAllByProductId(productToSave);

        List<Image> listaDeImagens = new ArrayList<>(allByProductId);
        for(Image i: listaDeImagens){
            boolean contains = list.contains(i.getId());
            if(contains){
                listaDeImagens.remove(i);
            }
        }

        List<Image> imagesToSave = new ArrayList<>();
        for(Image i: listaDeImagens){
            if(Boolean.TRUE.equals(i.getIsDefault())){
                imagesToSave.add(i);
            }
        }

        if(imagesToSave.isEmpty()){
            listaDeImagens.get(0).setIsDefault(true);
        }

        imageRepository.deleteAllById(list);
        imageRepository.saveAll(listaDeImagens);
    }
}