package br.com.projetointegrador.store.mock.generators;

import br.com.projetointegrador.store.mock.models.ProductMocks;
import br.com.projetointegrador.store.model.Product;

import java.util.Collections;
import java.util.List;

public class ProductMockGenerator {

    public static Product generateProduct(){
        return ProductMocks.getProduct();
    }

    public static List<Product> generateProductList(){
        return Collections.singletonList(ProductMocks.getProduct());
    }
}
