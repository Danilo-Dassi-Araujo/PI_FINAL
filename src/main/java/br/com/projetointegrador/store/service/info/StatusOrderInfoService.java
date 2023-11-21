package br.com.projetointegrador.store.service.info;

import br.com.projetointegrador.store.dto.response.StatusOrderInfoResponseDTO;
import br.com.projetointegrador.store.enums.AddressTypeEnum;
import br.com.projetointegrador.store.enums.StatusOrderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusOrderInfoService {

    public List<StatusOrderInfoResponseDTO> getStatusOrderList() {
        List<StatusOrderInfoResponseDTO> statusOrderInfoResponseDTOS = new ArrayList<>();

        for(StatusOrderEnum statusOrderEnum: StatusOrderEnum.values()){
            StatusOrderInfoResponseDTO statusOrder = StatusOrderInfoResponseDTO
                    .builder()
                    .id(statusOrderEnum.getId())
                    .name(statusOrderEnum.getName())
                    .build();
            statusOrderInfoResponseDTOS.add(statusOrder);
        }

        return statusOrderInfoResponseDTOS;
    }
}