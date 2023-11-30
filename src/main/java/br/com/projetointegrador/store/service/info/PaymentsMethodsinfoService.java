package br.com.projetointegrador.store.service.info;

import br.com.projetointegrador.store.dto.response.order.PaymentMethodDTO;
import br.com.projetointegrador.store.enums.PaymentsMethodsEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsMethodsinfoService {

    public List<PaymentMethodDTO> getPaymentsMethods() {
        List<PaymentMethodDTO> paymentsMethodsList = new ArrayList<>();

        Arrays.stream(PaymentsMethodsEnum.values())
                .toList()
                .forEach(e -> paymentsMethodsList.add(PaymentMethodDTO.builder().name(e.getPaymentMethod()).id(e.getId()).build()));

        return paymentsMethodsList;
    }
}
