package br.com.projetointegrador.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShippingsEnum {

    SEDEX(1,"Sedex", "Em 6 dias",29.99),
    CORREIOS(2,"Correios","Em 10 dias", 13.0),
    EXCARGO(3, "Excargo","Em 2 dias", 49.9);

    private Integer id;
    private String name;
    private String time;
    private Double price;

    public static Double getByIdForFront(Integer id) {
        for (ShippingsEnum status : ShippingsEnum.values()) {
            if (status.getId().equals(id)) {
                return status.getPrice();
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

    public static ShippingsEnum getByIdShipping(Integer id) {
        for (ShippingsEnum status : ShippingsEnum.values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }
}