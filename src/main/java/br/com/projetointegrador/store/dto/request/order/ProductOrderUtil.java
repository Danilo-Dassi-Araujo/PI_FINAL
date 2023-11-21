package br.com.projetointegrador.store.dto.request.order;

import br.com.projetointegrador.store.model.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductOrderUtil {

    private Product product;
    private String quantity;
}
