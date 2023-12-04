package br.com.projetointegrador.store.service.info;

import br.com.projetointegrador.store.dto.response.UserRoleInfoResponseDTO;
import br.com.projetointegrador.store.enums.StatusOrderEnum;
import br.com.projetointegrador.store.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleInfoService {

    public List<UserRoleInfoResponseDTO> getStatusOrderList() {
        List<UserRoleInfoResponseDTO> statusOrderInfoResponseDTOS = new ArrayList<>();

        for (UserRole role : UserRole.values()) {
            UserRoleInfoResponseDTO roles = UserRoleInfoResponseDTO
                    .builder()
                    .nameEnum(role.name())
                    .id(role.getId())
                    .name(role.getName())
                    .build();
            if (!UserRole.CLIENT.getName().equals(roles.getName())) {
                statusOrderInfoResponseDTOS.add(roles);
            }
        }

        return statusOrderInfoResponseDTOS;
    }

}