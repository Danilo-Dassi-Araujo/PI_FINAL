package br.com.projetointegrador.store.service.info;

import br.com.projetointegrador.store.dto.response.ShippingsInfoResponseDTO;
import br.com.projetointegrador.store.dto.response.StatusOrderInfoResponseDTO;
import br.com.projetointegrador.store.enums.ShippingsEnum;
import br.com.projetointegrador.store.enums.StatusOrderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingsInfoService {

    public List<ShippingsInfoResponseDTO> getShippings(){
        List<ShippingsInfoResponseDTO> statusOrderInfoResponseDTOS = new ArrayList<>();

        for(ShippingsEnum statusOrderEnum: ShippingsEnum.values()){
            ShippingsInfoResponseDTO statusOrder = ShippingsInfoResponseDTO
                    .builder()
                    .id(statusOrderEnum.getId())
                    .name(statusOrderEnum.getName())
                    .price(statusOrderEnum.getPrice())
                    .time(statusOrderEnum.getTime())
                    .build();
            statusOrderInfoResponseDTOS.add(statusOrder);
        }

        return statusOrderInfoResponseDTOS;
    }
}
