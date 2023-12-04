package br.com.projetointegrador.store.service.order;

import br.com.projetointegrador.store.dto.response.order.*;
import br.com.projetointegrador.store.enums.PaymentsMethodsEnum;
import br.com.projetointegrador.store.enums.ShippingsEnum;
import br.com.projetointegrador.store.enums.StatusOrderEnum;
import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Order;
import br.com.projetointegrador.store.model.OrderProduct;
import br.com.projetointegrador.store.model.Product;
import br.com.projetointegrador.store.repository.ImageRepository;
import br.com.projetointegrador.store.repository.OrderProductRepository;
import br.com.projetointegrador.store.repository.OrderRepository;
import br.com.projetointegrador.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetOrderByCodeService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final ImageRepository imageRepository;

    public OrderResponseCodeDTO getByOrderCode(String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode);

        order.getDeliveryAddress().setClient(null);
        order.getCliente().setSenha("");

        PaymentsMethodsEnum paymentsMethod = PaymentsMethodsEnum.getById(order.getPaymentMethodId());

        PaymentMethodDTO paymentMethodDTO = PaymentMethodDTO
                .builder()
                .id(paymentsMethod.getId())
                .name(paymentsMethod.getPaymentMethod())
                .build();

        Double valorFrete = ShippingsEnum.getByIdForFront(order.getShippingId());

        List<OrderProduct> orders = orderProductRepository.findAllByOrderId(order.getId());
        List<OrderProductDTO> listProducts = new ArrayList<>();
        for(OrderProduct orderItem: orders){
            Product productToSend = productRepository.findById(orderItem.getProduct().getId()).orElse(null);
            if(!ObjectUtils.isEmpty(productToSend)){
                List<Image> allByProductId = imageRepository.findAllByProductId(productToSend);
                DefaultImageDTO imageDefault = null;
                for(Image imagem: allByProductId){
                    if(Boolean.TRUE.equals(imagem.getIsDefault())){
                       imageDefault = DefaultImageDTO
                                .builder()
                                .path(imagem.getPath())
                                .build();
                    }
                }

                ProductDTO productToResponse = ProductDTO
                        .builder()
                        .id(productToSend.getId())
                        .rating(productToSend.getRate())
                        .name(productToSend.getName())
                        .price(productToSend.getPrice())
                        .description(productToSend.getDescription())
                        .stock(orderItem.getQuantity())
                        .isActive(productToSend.getIsActive())
                        .default_image(imageDefault)
                        .build();



                OrderProductDTO productsResponse = OrderProductDTO
                        .builder()
                        .product(productToResponse)
                        .build();

                listProducts.add(productsResponse);
            }
        }
        ShippingsEnum shipping = ShippingsEnum.getByIdShipping(order.getShippingId());
        ShippingDTO shippingResponse = ShippingDTO
                .builder()
                .id(shipping.getId())
                .name(shipping.getName())
                .price(shipping.getPrice())
                .time(shipping.getTime())
                .build();

        String statusFront = StatusOrderEnum.getByIdForFront(order.getStatusId());

        return OrderResponseCodeDTO
                .builder()
                .id(order.getId())
                .value(order.getValue())
                .cliente(order.getCliente())
                .cardPayment(order.getCardPayment())
                .deliveryAddress(order.getDeliveryAddress())
                .shippingId(order.getShippingId())
                .paymentMethod(paymentMethodDTO)
                .statusId(order.getStatusId())
                .paymentMethodId(order.getPaymentMethodId())
                .orderCode(orderCode)
                .status(statusFront)
                .date(order.getDate())
                .price(valorFrete)
                .products(listProducts)
                .shipping(shippingResponse)
                .build();
    }

}
