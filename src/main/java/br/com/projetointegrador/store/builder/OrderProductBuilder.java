package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.request.order.ProductOrderUtil;
import br.com.projetointegrador.store.model.Order;
import br.com.projetointegrador.store.model.OrderProduct;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderProductBuilder {

    public static List<OrderProduct> buildFrom(List<ProductOrderUtil> productOrderUtil, Order order) {

        List<OrderProduct> listOrderProduct = new ArrayList<>();

        for (ProductOrderUtil orderProductRequestDTO : productOrderUtil) {
            OrderProduct orderProduct = OrderProduct
                    .builder()
                    .product(orderProductRequestDTO.getProduct())
                    .order(order)
                    .quantity(orderProductRequestDTO.getQuantity())
                    .build();
            listOrderProduct.add(orderProduct);
        }
        return listOrderProduct;
    }
}