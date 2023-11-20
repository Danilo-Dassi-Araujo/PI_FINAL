package br.com.projetointegrador.store.utils;

import br.com.projetointegrador.store.enums.GenderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenderUtils {
    public String getGender(Integer id) {
        String idGender = id.toString();
        for (GenderEnum a : GenderEnum.values()) {
            if (idGender.equals(a.getId())) {
                return a.getGender();
            }
        }
        return null;
    }
}
