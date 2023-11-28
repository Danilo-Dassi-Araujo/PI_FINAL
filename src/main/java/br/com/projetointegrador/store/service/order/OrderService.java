package br.com.projetointegrador.store.service.order;

import br.com.projetointegrador.store.builder.OrderBuilder;
import br.com.projetointegrador.store.builder.OrderProductBuilder;
import br.com.projetointegrador.store.dto.request.order.OrderProductRequestDTO;
import br.com.projetointegrador.store.dto.request.order.OrderRequestDTO;
import br.com.projetointegrador.store.dto.request.order.ProductOrderUtil;
import br.com.projetointegrador.store.model.*;
import br.com.projetointegrador.store.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final CardPaymentsRepository cardPaymentsRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public void registerOrder(OrderRequestDTO orderRequestDTO) throws Exception {

        if (ObjectUtils.isEmpty(orderRequestDTO)) {
            throw new Exception("Request está vazia!");
        }

        Address addressToOrder = addressRepository.findById(orderRequestDTO.getDelivery_address_id()).orElse(null);
        Client clientToOrder = clientRepository.findById(orderRequestDTO.getUser_id()).orElse(null);
        List<CardPayments> cardPaymentsList = cardPaymentsRepository.findAll();

        List<CardPayments> list = cardPaymentsList.stream().filter(card -> {
            assert clientToOrder != null;
            return clientToOrder.getId().equals(card.getId());
        }).toList();

        Order orderToSave = OrderBuilder.buildFrom(orderRequestDTO, addressToOrder, clientToOrder, list);

        Order savedOrder = orderRepository.save(orderToSave);

        List<ProductOrderUtil> listProducts = new ArrayList<>();

        for (OrderProductRequestDTO orderProductRequestDTO : orderRequestDTO.getProducts()) {

            Product product = productRepository.findById(orderProductRequestDTO.getProduct_id()).orElse(null);

            ProductOrderUtil productOrderUtil = ProductOrderUtil
                    .builder()
                    .product(product)
                    .quantity(orderProductRequestDTO.getQuantity())
                    .build();

            listProducts.add(productOrderUtil);
        }

        List<OrderProduct> orderProductToSave = OrderProductBuilder.buildFrom(listProducts, savedOrder);
        for(OrderProduct orderProduct: orderProductToSave){
            orderProductRepository.save(orderProduct);
        }
    }
}