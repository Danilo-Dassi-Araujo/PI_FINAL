package br.com.projetointegrador.store.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends HttpException {

    public UserNotFoundException() {
        super("User not found.", HttpStatus.NOT_FOUND);
    }

}
