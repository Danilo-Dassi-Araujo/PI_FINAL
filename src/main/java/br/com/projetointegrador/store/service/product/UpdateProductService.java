package br.com.projetointegrador.store.service.product;


import br.com.projetointegrador.store.builder.ProductBuilder;
import br.com.projetointegrador.store.dto.request.UpdateProductImage;
import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;
import br.com.projetointegrador.store.enums.UserRole;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import br.com.projetointegrador.store.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ImageSave imageSave;
    private final ImageRemove imageRemove;

    public void updateProduct(UpdateProductRequestDTO updateProductRequestDTO) throws Exception {
        if (ObjectUtils.isEmpty(updateProductRequestDTO)) {
            throw new Exception("Request vazia!");
        }

        ValidatorUtils.validateUpdateProduct(updateProductRequestDTO);

        if (ObjectUtils.isEmpty(updateProductRequestDTO.getRole())) {
            updateProductRequestDTO.setRole("Administrador");
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


        imagesRest(updateProductRequestDTO, productToSave);

        if (!ObjectUtils.isEmpty(updateProductRequestDTO.getNewImages())) {
            imagesToSave(updateProductRequestDTO, productToSave);
        }

        if (!ObjectUtils.isEmpty(updateProductRequestDTO.getImagesToDelete())) {
            imagesToDelete(updateProductRequestDTO, productToSave);
        }
    }

    private void imagesToSave(UpdateProductRequestDTO updateProductRequestDTO, Product productToSave) {

        List<UpdateProductImage> imagesIsDefaultTrue = new ArrayList<>();
        for (UpdateProductImage image : updateProductRequestDTO.getNewImages()) {
            if (Boolean.TRUE.equals(image.getIsDefault())) {
                imagesIsDefaultTrue.add(image);
            }
        }

        if (!imagesIsDefaultTrue.isEmpty()) {
            List<Image> allByProductId = imageRepository.findAllByProductId(productToSave);
            allByProductId
                    .forEach(image -> image.setIsDefault(false));

            for (UpdateProductImage image : updateProductRequestDTO.getNewImages()) {
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

            for (UpdateProductImage image : updateProductRequestDTO.getNewImages()) {
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
        for (UpdateProductImage image : updateProductRequestDTO.getImages()) {
            if (Boolean.TRUE.equals(image.getIsDefault())) {
                imagesIsDefaultTrue.add(image);
            }
        }

        if (!imagesIsDefaultTrue.isEmpty()) {
            List<Image> allByProductId = imageRepository.findAllByProductId(productToSave);
            allByProductId
                    .forEach(image -> image.setIsDefault(false));
            for (Image image : allByProductId) {
                if (image.getId().equals(imagesIsDefaultTrue.get(0).getId())) {
                    image.setIsDefault(Boolean.TRUE);
                }
            }

            imageRepository.saveAll(allByProductId);
        }

        if (imagesIsDefaultTrue.isEmpty()) {

            List<Image> allByProductId = imageRepository.findAllByProductId(productToSave);
            allByProductId
                    .forEach(image -> image.setIsDefault(false));
            imageRepository.saveAll(allByProductId);
        }
    }

    private void imagesToDelete(UpdateProductRequestDTO updateProductRequestDTO, Product productToSave) throws IOException {
        List<Integer> list =
                updateProductRequestDTO
                        .getImagesToDelete()
                        .stream()
                        .map(UpdateProductImage::getId).toList();
        List<Image> allByProductId = imageRepository.findAllByProductId(productToSave);

        List<Image> listaDeImagens = new ArrayList<>(allByProductId);
        listaDeImagens.removeIf(i -> list.contains(i.getId()));

        List<Image> imagesToSave = new ArrayList<>();
        for (Image i : listaDeImagens) {
            if (Boolean.TRUE.equals(i.getIsDefault())) {
                imagesToSave.add(i);
            }
        }

        if (imagesToSave.isEmpty()) {
            listaDeImagens.get(0).setIsDefault(true);
        }

        list.forEach(imageRepository::deleteById);
        imageRepository.saveAll(listaDeImagens);
        for (UpdateProductImage i : updateProductRequestDTO.getImagesToDelete()) {
            String pathImage = productToSave.getId().toString() + "/" + i.getPath();
            imageRemove.removeImage("produtosImagem/", pathImage);
        }
    }
}