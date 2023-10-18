package br.com.projetointegrador.store.exceptions.handler;


import br.com.projetointegrador.store.exceptions.HttpException;
import br.com.projetointegrador.store.exceptions.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<Map<String, Object>> handleHttpException(HttpException ex) {
        return ex.toResponseEntity();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        return this.defaultResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ResponseEntity defaultResponseError(HttpStatus httpStatus, Exception ex) {
        var responseBody = ErrorResponseDTO.builder()
                .status(httpStatus.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .error(httpStatus.getReasonPhrase())
                .cause(ex.getClass().getSimpleName())
                .build();

        return ResponseEntity.status(httpStatus).body(responseBody);
    }

}
