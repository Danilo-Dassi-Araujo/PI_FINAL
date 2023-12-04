package br.com.projetointegrador.store.service.order;

import br.com.projetointegrador.store.dto.response.order.StatusOrderIdResponseDTO;
import br.com.projetointegrador.store.enums.StatusOrderEnum;
import br.com.projetointegrador.store.model.Order;
import br.com.projetointegrador.store.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetOrderStatusService {

    private final OrderRepository orderRepository;

    public StatusOrderIdResponseDTO getOrderStatus(UUID uuid) throws Exception {

        Order order = orderRepository.findById(uuid).orElse(null);

        if(ObjectUtils.isEmpty(order)){
            throw new Exception("Nenhuma ordem encontrada!");
        }
        StatusOrderEnum status = StatusOrderEnum.getById(order.getStatusId());
        return StatusOrderIdResponseDTO.builder().statusId(status.getId()).build();
    }
}
