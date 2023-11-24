package br.com.projetointegrador.store.service;

import br.com.projetointegrador.store.dto.request.ProductRequestDTO;
import br.com.projetointegrador.store.mock.generators.ProductMockGenerator;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import br.com.projetointegrador.store.service.product.ImageSave;
import br.com.projetointegrador.store.service.product.RegisterProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static br.com.projetointegrador.store.mock.generators.ProductRequestDTOGenerator.generateProductImageRequestDTO;
import static br.com.projetointegrador.store.mock.generators.ProductRequestDTOGenerator.generateProductRequestDTO;
import static br.com.projetointegrador.store.util.ConstantMocks.TESTE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductRegisterServiceTest {

    @InjectMocks
    private RegisterProductService registerProductService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private ImageSave imageSave;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registerProductService = new RegisterProductService(
                productRepository,
                imageRepository,
                imageSave
        );
    }

    @DisplayName("Testando registro de um produto sem imagens")
    @Test
    void testRegisterProduct() throws Exception {
        Product product = ProductMockGenerator.generateProduct();
        ProductRequestDTO productRequestDTO = generateProductRequestDTO();

        when(productRepository.save(any())).thenReturn(product);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);


        registerProductService.registerProduct(productRequestDTO);

        verify(imageRepository, Mockito.times(0)).save(any());
        verify(productRepository, Mockito.times(1)).save(any());
    }

    @DisplayName("Testando registro de um produto com imagens")
    @Test
    void testRegisterProductWithImage() throws Exception {
        Product product = ProductMockGenerator.generateProduct();
        ProductRequestDTO productRequestDTO = generateProductImageRequestDTO();

        when(productRepository.save(any())).thenReturn(product);
        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);


        registerProductService.registerProduct(productRequestDTO);

        verify(imageSave, Mockito.times(1)).saveImage(any(), any());
        verify(productRepository, Mockito.times(1)).save(any());
        verify(imageRepository, Mockito.times(1)).saveAll(any());
    }

}