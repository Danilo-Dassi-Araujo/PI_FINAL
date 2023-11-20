//package br.com.projetointegrador.store.service;
//
//import br.com.projetointegrador.store.dto.request.ImageRequestDTO;
//import br.com.projetointegrador.store.mock.generators.ProductMockGenerator;
//import br.com.projetointegrador.store.model.Product;
//import br.com.projetointegrador.store.repository.ImageRepository;
//import br.com.projetointegrador.store.repository.ProductRepository;
//import br.com.projetointegrador.store.service.product.ImageSave;
//import br.com.projetointegrador.store.service.product.RegisterProductService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static br.com.projetointegrador.store.util.ConstantMocks.TESTE;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class ProductRegisterServiceTest {
//
//    @InjectMocks
//    private RegisterProductService registerProductService;
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private ImageRepository imageRepository;
//
//    @Mock
//    private ImageSave imageSave;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        registerProductService = new RegisterProductService(
//                productRepository,
//                imageRepository,
//                imageSave
//        );
//    }
//
//
//    @Test
//    public void testRegisterProduct() throws Exception {
//        Product product = ProductMockGenerator.generateProduct();
//
//
//        when(productRepository.save(any())).thenReturn(product);
//        when(imageSave.saveImage(any(), any())).thenReturn(TESTE);
//
//        registerProductService.registerProduct();
//
//        verify(imageRepository, Mockito.times(0)).save(any());
//        verify(productRepository, Mockito.times(0)).save(any());
////        when(imageRepository.saveAll(any())).thenReturn();
//
//    }
//
//}
