package br.com.projetointegrador.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOrderEnum {

    AGUARDANDO_PAGAMENTO(1, "Aguardando Pagamento"),
    PAGAMENTO_REJEITADO(2, "Pagamento Rejeitado"),
    PAGAMENTO_SUCESSO(3, "Pagamento com Sucesso"),
    AGUARDADNO_RETIRADA(4, "Aguardando Retirada"),
    EM_TRANSITO(5, "Em Trânsito"),
    ENTREGUE(6, "Entregue");

    private Integer id;
    private String name;

    public static StatusOrderEnum getById(Integer id) {
        for (StatusOrderEnum status : StatusOrderEnum.values()) {
            if (status.id.equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

}