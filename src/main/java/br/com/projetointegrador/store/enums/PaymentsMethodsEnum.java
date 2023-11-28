package br.com.projetointegrador.store.enums;

import br.com.projetointegrador.store.dto.response.order.PaymentMethodDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentsMethodsEnum {

    CARTAO_DE_CREDITO(1,"Cartão de Crédito"),
    BOLETO(2,"Boleto");

    private final Integer id;
    private final String paymentMethod;


    public static PaymentsMethodsEnum getById(int id) {
        for (PaymentsMethodsEnum paymentMethod : PaymentsMethodsEnum.values()) {
            if (paymentMethod.getId() == id) {
                return paymentMethod;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

}