package br.com.projetointegrador.store.exceptions;

import br.com.projetointegrador.store.exceptions.dto.ErrorResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Getter
public class HttpException extends RuntimeException {

    private Throwable cause;
    private HttpStatus httpStatus;

    public HttpException(String message, HttpStatus httpStatus) {
        super(message);

        this.httpStatus = httpStatus;
    }

    public HttpException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message);

        this.cause = cause;
        this.httpStatus = httpStatus;
    }

    public ResponseEntity toResponseEntity() {
        var responseBody = ErrorResponseDTO.builder()
                .status(this.httpStatus.value())
                .message(super.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        if (!ObjectUtils.isEmpty(this.cause)) {
            responseBody.setError(this.cause.getMessage());
            responseBody.setCause(this.cause.getClass().getSimpleName());
        }

        return ResponseEntity.status(this.httpStatus).body(responseBody);
    }

}
