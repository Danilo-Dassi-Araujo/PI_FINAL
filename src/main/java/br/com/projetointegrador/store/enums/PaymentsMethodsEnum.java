package br.com.projetointegrador.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentsMethodsEnum {

    CARTAO_DE_CREDITO("Cartão de Crédito"),
    BOLETO("Boleto");

    private final String paymentMethod;
}
