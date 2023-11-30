package br.com.projetointegrador.store.service.order;

import br.com.projetointegrador.store.dto.response.order.OrderResponseCodeDTO;
import br.com.projetointegrador.store.dto.response.order.PaymentMethodDTO;
import br.com.projetointegrador.store.enums.PaymentsMethodsEnum;
import br.com.projetointegrador.store.model.Order;
import br.com.projetointegrador.store.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrderByCodeService {

    private final OrderRepository orderRepository;

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
                .date(order.getDate())
                .build();
    }

}
