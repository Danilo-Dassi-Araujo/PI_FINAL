package br.com.projetointegrador.store.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends HttpException {

    public ProductNotFoundException() {
        super("Product not found.", HttpStatus.NOT_FOUND);
    }

}
