package br.com.projetointegrador.store.service.info;

import br.com.projetointegrador.store.enums.PaymentsMethodsEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsMethodsinfoService {

    public List<String> getPaymentsMethods() {
        List<String> paymentsMethodsList = new ArrayList<>();

        Arrays.stream(PaymentsMethodsEnum.values())
                .toList()
                .forEach(e -> paymentsMethodsList.add(e.getPaymentMethod()));

        return paymentsMethodsList;
    }
}
