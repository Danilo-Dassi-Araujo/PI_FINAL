package br.com.projetointegrador.store.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {

    private String message;

    private int status;

    private LocalDateTime timestamp;

    private String cause;

    private String error;

}