package br.com.projetointegrador.store.controller.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductResponseDTO {

    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private double rate;

}
