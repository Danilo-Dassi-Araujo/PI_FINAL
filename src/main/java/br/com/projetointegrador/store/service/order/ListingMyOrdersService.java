package br.com.projetointegrador.store.service.order;

import br.com.projetointegrador.store.dto.response.order.*;
import br.com.projetointegrador.store.enums.PaymentsMethodsEnum;
import br.com.projetointegrador.store.enums.StatusOrderEnum;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.model.Order;
import br.com.projetointegrador.store.model.OrderProduct;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.ClientRepository;
import br.com.projetointegrador.store.repository.OrderProductRepository;
import br.com.projetointegrador.store.repository.OrderRepository;
import br.com.projetointegrador.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static br.com.projetointegrador.store.enums.UserRole.CLIENT;

@Service
@RequiredArgsConstructor
public class ListingMyOrdersService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public List<AllMyOrdersResponseDTO> getAllMyOrders(UUID uuid) {
        List<Order> allOrders = orderRepository.findAll();

        User usuarioLogado = userRepository.findById(uuid).orElse(null);

        UUID idClienteLogado;
        if(!ObjectUtils.isEmpty(usuarioLogado)){
            String email = usuarioLogado.getEmail();
            Client clientByEmail = clientRepository.findByEmail(email);
            idClienteLogado = clientByEmail.getId();
        } else {
            idClienteLogado = null;
        }

        if (!allOrders.isEmpty()) {
            List<Order> allMyOrders = allOrders
                    .stream()
                    .filter(order -> order.getCliente().getId().equals(idClienteLogado))
                    .toList();

            List<AllMyOrdersResponseDTO> listaResponse = new ArrayList<>();
            for (Order order : allMyOrders) {
                UserOrderResponseDTO user = UserOrderResponseDTO
                        .builder()
                        .id(order.getCliente().getId())
                        .name(order.getCliente().getNomeCompleto())
                        .role_id(CLIENT.getId())
                        .email(order.getCliente().getEmail())
                        .cpf(order.getCliente().getCpf())
                        .build();

                DeliveryAddressResponseDTO deliveryAddressDTO = DeliveryAddressResponseDTO
                        .builder()
                        .id(order.getDeliveryAddress().getIdEndereco())
                        .user_id(order.getCliente().getId())
                        .cep(order.getDeliveryAddress().getCep())
                        .city(order.getDeliveryAddress().getCidade())
                        .uf(order.getDeliveryAddress().getUf())
                        .isActive(order.getDeliveryAddress().getIsDefault())
                        .complement(order.getDeliveryAddress().getComplemento())
                        .isDefault(order.getDeliveryAddress().getIsDefault())
                        .neighborhood(order.getDeliveryAddress().getBairro())
                        .number(order.getDeliveryAddress().getNumero())
                        .street(order.getDeliveryAddress().getLogradouro())
                        .build();
                CardPaymentResponseDTO cardPaymentDTO = null;
                if (!ObjectUtils.isEmpty(order.getCardPayment())) {
                    cardPaymentDTO = CardPaymentResponseDTO
                            .builder()
                            .id(order.getCardPayment().getId())
                            .card_number(order.getCardPayment().getCardNumber())
                            .code(order.getCardPayment().getCode())
                            .expire_date(LocalDate.now().toString())
                            .portion_value(order.getCardPayment().getPortionsValue())
                            .portions(order.getCardPayment().getPortions())
                            .build();
                }

                List<OrderProductResponseDTO> orderProductDTOS = new ArrayList<>();
                List<OrderProduct> allByOrderOrderCode = orderProductRepository.findAllByOrderOrderCode(order.getOrderCode());
                for (OrderProduct orderProduct : allByOrderOrderCode) {
                    ProductResponseDTO build = ProductResponseDTO
                            .builder()
                            .id(orderProduct.getProduct().getId())
                            .stock(orderProduct.getProduct().getStockQuantity())
                            .price(orderProduct.getProduct().getPrice())
                            .description(orderProduct.getProduct().getDescription())
                            .rating(orderProduct.getProduct().getRate())
                            .isActive(orderProduct.getProduct().getIsActive())
                            .name(orderProduct.getProduct().getName())
                            .build();

                    OrderProductResponseDTO orderProducts = OrderProductResponseDTO
                            .builder()
                            .id(orderProduct.getId())
                            .order_id(orderProduct.getOrder().getId())
                            .product_id(orderProduct.getProduct().getId())
                            .product(build)
                            .quantity(Integer.parseInt(orderProduct.getQuantity()))
                            .build();

                    orderProductDTOS.add(orderProducts);

                }

                StatusOrderEnum enumStatus = StatusOrderEnum.getById(order.getStatusId());

                StatusDTO status = StatusDTO
                        .builder()
                        .id(enumStatus.getId())
                        .name(enumStatus.getName())
                        .build();

                PaymentsMethodsEnum paymentsMethodsEnum = PaymentsMethodsEnum.getById(order.getPaymentMethodId());

                PaymentMethodDTO paymentMethodDTO = PaymentMethodDTO
                        .builder()
                        .id(paymentsMethodsEnum.getId())
                        .name(paymentsMethodsEnum.getPaymentMethod())
                        .build();

                AllMyOrdersResponseDTO orderResponse = AllMyOrdersResponseDTO
                        .builder()
                        .id(order.getId())
                        .status_id(order.getStatusId())
                        .card_payment_id(null)
                        .payment_method_id(order.getPaymentMethodId())
                        .card_payment(cardPaymentDTO)
                        .order_product(orderProductDTOS)
                        .order_code(order.getOrderCode())
                        .user(user)
                        .date(order.getDate())
                        .value(order.getValue())
                        .delivery_address_id(deliveryAddressDTO.getId())
                        .delivery_address(deliveryAddressDTO)
                        .user_id(order.getCliente().getId())
                        .payment_method(paymentMethodDTO)
                        .status(status)
                        .build();
                listaResponse.add(orderResponse);
            }
            return listaResponse;
        }
        return Collections.emptyList();
    }
}