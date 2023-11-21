package br.com.projetointegrador.store.dto.response.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {

    private int id;
    private String name;
    private String rating;
    private String description;
    private double price;
    private int stock;
    private int isActive;
    private DefaultImageDTO default_image;

}
