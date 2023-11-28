package br.com.projetointegrador.store.service;

import br.com.projetointegrador.store.dto.request.UpdateProductRequestDTO;
import br.com.projetointegrador.store.mock.generators.ProductMockGenerator;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import br.com.projetointegrador.store.service.product.ImageRemove;
import br.com.projetointegrador.store.service.product.ImageSave;
import br.com.projetointegrador.store.service.product.UpdateProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static br.com.projetointegrador.store.mock.generators.Image.generateListImages;
import static br.com.projetointegrador.store.mock.generators.ProductMockGenerator.generateProductOptional;
import static br.com.projetointegrador.store.mock.generators.UpdateProductRequestDTO.*;
import static br.com.projetointegrador.store.util.ConstantMocks.ROLE;
import static br.com.projetointegrador.store.util.ConstantMocks.TESTE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateProductServiceTest {


    @InjectMocks
    private UpdateProductService updateProductService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private ImageSave imageSave;
    @Mock
    private ImageRemove imageRemove;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        updateProductService = new UpdateProductService(
                productRepository,
                imageRepository,
                imageSave,
                imageRemove
        );
    }

    @DisplayName("Testando update com a request vazia")
    @Test
    void testUpdateProductEmptyRequest() {
        Product product = ProductMockGenerator.generateProduct();
        UpdateProductRequestDTO updateProductRequestDTO = null;

        when(productRepository.save(any())).thenReturn(product);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);

        Exception exception = Assertions.assertThrows(Exception.class, () -> updateProductService.updateProduct(updateProductRequestDTO));

        String expectedError = "Request vazia!";

        assertEquals(expectedError, exception.getMessage());

        verify(imageRepository, Mockito.times(0)).save(any());
        verify(productRepository, Mockito.times(0)).save(any());
    }

    @DisplayName("Testando update de um produto como sem perfil de estoquista")
    @Test
    void testUpdateProductLikeStock() {
        Product product = ProductMockGenerator.generateProduct();
        UpdateProductRequestDTO updateProductRequestDTO = generateUpdateProductRequestDTO();
        updateProductRequestDTO.setRole(ROLE);

        when(productRepository.save(any())).thenReturn(product);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);

        Exception exception = Assertions.assertThrows(Exception.class, () -> updateProductService.updateProduct(updateProductRequestDTO));


        String expectedError = "Somente administradores podem alterar os produtos!";

        assertEquals(expectedError, exception.getMessage());

        verify(imageRepository, Mockito.times(0)).save(any());
        verify(productRepository, Mockito.times(0)).save(any());
    }

    @DisplayName("Testando update de um produto que não é encontrado")
    @Test
    void testUpdateProductNotFoundProduct() {
        Product product = null;
        UpdateProductRequestDTO updateProductRequestDTO = generateUpdateProductRequestDTO();

        when(productRepository.save(any())).thenReturn(product);

        Exception exception = Assertions.assertThrows(Exception.class, () -> updateProductService.updateProduct(updateProductRequestDTO));

        String expectedError = "Nenhum produto encontrado pelo id: " + updateProductRequestDTO.getId();

        assertEquals(expectedError, exception.getMessage());

        verify(productRepository, Mockito.times(1)).findById(any());
        verify(imageRepository, Mockito.times(0)).save(any());
        verify(productRepository, Mockito.times(0)).save(any());
    }

    @DisplayName("Testando update de um produto e salvando!")
    @Test
    void testUpdateProductSaved() throws Exception {
        Product product = ProductMockGenerator.generateProduct();
        Optional<Product> optionalProduct = generateProductOptional();
        UpdateProductRequestDTO updateProductRequestDTO = generateUpdateProductRequestDTO();

        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.findById(any())).thenReturn(optionalProduct);

        updateProductService.updateProduct(updateProductRequestDTO);

        verify(productRepository, Mockito.times(1)).findById(any());
        verify(imageRepository, Mockito.times(0)).save(any());
        verify(productRepository, Mockito.times(1)).save(any());
    }

    @DisplayName("Testando update de um produto com novas imagens!")
    @Test
    void testUpdateProductSavedWithNewImages() throws Exception {
        Product product = ProductMockGenerator.generateProduct();
        Optional<Product> optionalProduct = generateProductOptional();
        UpdateProductRequestDTO updateProductRequestDTO = generateUpdateProductRequestWithNewImagesDTO();
        List<Image> imageList = generateListImages();

        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(imageRepository.findAllByProductId(any())).thenReturn(imageList);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);

        updateProductService.updateProduct(updateProductRequestDTO);

        verify(productRepository, Mockito.times(1)).findById(any());
        verify(imageRepository, Mockito.times(0)).save(any());
        verify(productRepository, Mockito.times(1)).save(any());
        verify(imageRepository, Mockito.times(1)).saveAll(any());
    }

    @DisplayName("Testando update de um produto com novas imagens porem com elas vindo isDefault false!")
    @Test
    void testUpdateProductSavedWithNewImagesIsDefaultFalse() throws Exception {
        Product product = ProductMockGenerator.generateProduct();
        Optional<Product> optionalProduct = generateProductOptional();
        UpdateProductRequestDTO updateProductRequestDTO = generateUpdateProductRequestWithNewImagesDTO();
        updateProductRequestDTO.getNewImages().get(0).setIsDefault(Boolean.FALSE);
        List<Image> imageList = generateListImages();

        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(imageRepository.findAllByProductId(any())).thenReturn(imageList);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);

        updateProductService.updateProduct(updateProductRequestDTO);

        verify(productRepository, Mockito.times(1)).findById(any());
        verify(imageRepository, Mockito.times(0)).save(any());
        verify(productRepository, Mockito.times(1)).save(any());
        verify(imageRepository, Mockito.times(1)).saveAll(any());
    }

    @DisplayName("Testando update de um produto com imagens isDefault true!")
    @Test
    void testUpdateProductSavedWithImagesIsDefaultTrue() throws Exception {
        Product product = ProductMockGenerator.generateProduct();
        Optional<Product> optionalProduct = generateProductOptional();
        UpdateProductRequestDTO updateProductRequestDTO = generateUpdateProductRequestWithImagesDTO();
        List<Image> imageList = generateListImages();

        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(imageRepository.findAllByProductId(any())).thenReturn(imageList);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);

        updateProductService.updateProduct(updateProductRequestDTO);

        verify(productRepository, Mockito.times(1)).findById(any());
        verify(imageRepository, Mockito.times(0)).save(any());
        verify(productRepository, Mockito.times(1)).save(any());
        verify(imageRepository, Mockito.times(1)).saveAll(any());
    }

    @DisplayName("Testando update de um produto com novas imagens porem com elas vindo isDefault false!")
    @Test
    void testUpdateProductSavedWithImagesIsDefaultFalse() throws Exception {
        Product product = ProductMockGenerator.generateProduct();
        Optional<Product> optionalProduct = generateProductOptional();
        UpdateProductRequestDTO updateProductRequestDTO = generateUpdateProductRequestWithImagesDTO();
        updateProductRequestDTO.getImages().get(0).setIsDefault(Boolean.FALSE);
        List<Image> imageList = generateListImages();

        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(imageRepository.findAllByProductId(any())).thenReturn(imageList);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);

        updateProductService.updateProduct(updateProductRequestDTO);

        verify(productRepository, Mockito.times(1)).findById(any());
        verify(imageRepository, Mockito.times(0)).save(any());
        verify(productRepository, Mockito.times(1)).save(any());
        verify(imageRepository, Mockito.times(1)).saveAll(any());
    }

    @DisplayName("Testando update de um produto com imagens para deletar!")
    @Test
    void testUpdateProductSavedWithDeleteImages() throws Exception {
        Product product = ProductMockGenerator.generateProduct();
        Optional<Product> optionalProduct = generateProductOptional();
        UpdateProductRequestDTO updateProductRequestDTO = generateUpdateProductRequestWithImagesToDeleteDTO();
        List<Image> imageList = generateListImages();

        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(imageRepository.findAllByProductId(any())).thenReturn(imageList);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);

        updateProductService.updateProduct(updateProductRequestDTO);

        verify(productRepository, Mockito.times(1)).findById(any());
        verify(imageRepository, Mockito.times(1)).saveAll(any());
        verify(productRepository, Mockito.times(1)).save(any());
        verify(imageRepository, atLeast(1)).deleteById(any());
    }

    @DisplayName("Testando update de um produto com imagens para deletar!")
    @Test
    void testUpdateProductSavedWithDeleteImagesIsDefaultFalse() throws Exception {
        Product product = ProductMockGenerator.generateProduct();
        Optional<Product> optionalProduct = generateProductOptional();
        UpdateProductRequestDTO updateProductRequestDTO = generateUpdateProductRequestWithImagesToDeleteDTO();
        List<Image> imageList = generateListImages();
        imageList.get(1).setIsDefault(Boolean.FALSE);

        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.findById(any())).thenReturn(optionalProduct);
        when(imageRepository.findAllByProductId(any())).thenReturn(imageList);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);

        updateProductService.updateProduct(updateProductRequestDTO);

        verify(productRepository, Mockito.times(1)).findById(any());
        verify(imageRepository, Mockito.times(1)).saveAll(any());
        verify(productRepository, Mockito.times(1)).save(any());
        verify(imageRepository, atLeast(1)).deleteById(any());
    }
}