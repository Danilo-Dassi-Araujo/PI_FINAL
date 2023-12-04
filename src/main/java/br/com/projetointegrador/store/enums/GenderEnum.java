package br.com.projetointegrador.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {

    MASCULINO("1", "Masculino"),
    FEMININO("2", "Feminino"),
    NEUTRE("3", "Neutre");

    private final String id;
    private final String gender;

    public static GenderEnum getById(String id) {
        for (GenderEnum paymentMethod : GenderEnum.values()) {
            if (paymentMethod.getId().equals(id)) {
                return paymentMethod;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

}