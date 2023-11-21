package br.com.projetointegrador.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentsMethodsEnum {

    CARTAO_DE_CREDITO(1,"Cartão de Crédito"),
    BOLETO(2,"Boleto");

    private final Integer id;
    private final String paymentMethod;
}