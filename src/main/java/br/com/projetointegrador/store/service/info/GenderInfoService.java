package br.com.projetointegrador.store.service.info;

import br.com.projetointegrador.store.dto.response.GenderResponseDTO;
import br.com.projetointegrador.store.enums.GenderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderInfoService {

    public List<GenderResponseDTO> getGender() {
        List<GenderResponseDTO> paymentsMethodsList = new ArrayList<>();

        Arrays.stream(GenderEnum.values())
                .toList()
                .forEach(e -> paymentsMethodsList.add(GenderResponseDTO.builder().gender(e.getGender()).id(e.getId()).build()));

        return paymentsMethodsList;
    }
}
