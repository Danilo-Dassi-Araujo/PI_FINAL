package br.com.projetointegrador.store.utils;

import br.com.projetointegrador.store.enums.GenderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenderUtils {
    public String getGender(String id) {
        for (GenderEnum a : GenderEnum.values()) {
            if (id.equals(a.getId())) {
                return a.getGender();
            }
        }
        return null;
    }
}
