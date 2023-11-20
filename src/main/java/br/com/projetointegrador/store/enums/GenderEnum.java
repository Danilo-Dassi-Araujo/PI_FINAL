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
}