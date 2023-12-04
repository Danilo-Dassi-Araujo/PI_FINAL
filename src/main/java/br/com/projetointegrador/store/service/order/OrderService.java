package br.com.projetointegrador.store.service.order;

import br.com.projetointegrador.store.builder.OrderBuilder;
import br.com.projetointegrador.store.builder.OrderProductBuilder;
import br.com.projetointegrador.store.dto.request.order.OrderProductRequestDTO;
import br.com.projetointegrador.store.dto.request.order.OrderRequestDTO;
import br.com.projetointegrador.store.dto.request.order.ProductOrderUtil;
import br.com.projetointegrador.store.dto.response.order.OrderStringResponseDTO;
import br.com.projetointegrador.store.model.*;
import br.com.projetointegrador.store.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final CardPaymentsRepository cardPaymentsRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderStringResponseDTO registerOrder(OrderRequestDTO orderRequestDTO) throws Exception {

        if (ObjectUtils.isEmpty(orderRequestDTO)) {
            throw new Exception("Request está vazia!");
        }

        User usuarioLogado = userRepository.findById(orderRequestDTO.getUser_id()).orElse(null);

        UUID idClienteLogado;
        if(!ObjectUtils.isEmpty(usuarioLogado)){
            String email = usuarioLogado.getEmail();
            Client clientByEmail = clientRepository.findByEmail(email);
            idClienteLogado = clientByEmail.getId();
        } else {
            idClienteLogado = null;
        }

        Address addressToOrder = addressRepository.findById(orderRequestDTO.getDelivery_address_id()).orElse(null);
        assert idClienteLogado != null;
        Client clientToOrder = clientRepository.findById(idClienteLogado).orElse(null);
        List<CardPayments> cardPaymentsList = cardPaymentsRepository.findAll();

        List<CardPayments> list = cardPaymentsList.stream().filter(card -> {
            assert clientToOrder != null;
            return clientToOrder.getId().equals(card.getId());
        }).toList();

        Order orderToSave = OrderBuilder.buildFrom(orderRequestDTO, addressToOrder, clientToOrder, list);

        Order savedOrder = orderRepository.save(orderToSave);

        List<ProductOrderUtil> listProducts = new ArrayList<>();

        for (OrderProductRequestDTO orderProductRequestDTO : orderRequestDTO.getProducts()) {

            Product product = productRepository.findById(orderProductRequestDTO.getId()).orElse(null);

            ProductOrderUtil productOrderUtil = ProductOrderUtil
                    .builder()
                    .product(product)
                    .quantity(orderProductRequestDTO.getCounter())
                    .build();

            listProducts.add(productOrderUtil);
        }

        List<OrderProduct> orderProductToSave = OrderProductBuilder.buildFrom(listProducts, savedOrder);
        for(OrderProduct orderProduct: orderProductToSave){
            orderProductRepository.save(orderProduct);
        }
        return OrderStringResponseDTO
                .builder()
                .orderCode(orderProductToSave.get(0).getOrder().getOrderCode())
                .build();
    }
}