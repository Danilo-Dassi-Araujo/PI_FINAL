package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.request.order.OrderRequestDTO;
import br.com.projetointegrador.store.enums.StatusOrderEnum;
import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.model.CardPayments;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class OrderBuilder {

    public static Order buildFrom(OrderRequestDTO orderRequestDTO, Address addressToOrder, Client clientToOrder, CardPayments cardPayments) {

        return Order
                .builder()
                .cliente(clientToOrder)
                .deliveryAddress(addressToOrder)
                .paymentMethodId(orderRequestDTO.getPayment_method_id())
                .shippingId(orderRequestDTO.getShipping_id())
                .value(orderRequestDTO.getTotal_value())
                .cardPayment(ObjectUtils.isEmpty(cardPayments) ? null : cardPayments)
                .date(LocalDate.now())
                .statusId(StatusOrderEnum.AGUARDANDO_PAGAMENTO.getId())
                .orderCode(generateOrderCode())
                .build();
    }

    private static String generateOrderCode() {
        Random random = new Random();
        StringBuilder orderCode = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10); // Gera um número aleatório entre 0 e 9
            orderCode.append(digit);
        }

        return orderCode.toString();
    }
}
