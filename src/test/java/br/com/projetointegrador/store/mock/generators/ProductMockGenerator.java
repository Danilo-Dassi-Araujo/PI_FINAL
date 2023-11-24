package br.com.projetointegrador.store.mock.generators;

import br.com.projetointegrador.store.mock.models.ProductMock;
import br.com.projetointegrador.store.model.Product;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.projetointegrador.store.mock.models.ProductMock.getProductOptional;

public class ProductMockGenerator {

    public static Product generateProduct(){
        return ProductMock.getProduct();
    }
    public static Optional<Product> generateProductOptional(){
        return getProductOptional();
    }

    public static List<Product> generateProductList(){
        return Collections.singletonList(ProductMock.getProduct());
    }
}
