package br.com.projetointegrador.store.service.order;

import br.com.projetointegrador.store.dto.response.order.OrderStatusRequestDTO;
import br.com.projetointegrador.store.enums.StatusOrderEnum;
import br.com.projetointegrador.store.model.Order;
import br.com.projetointegrador.store.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeStatusOrderService {

    private final OrderRepository orderRepository;

    public void changeStatusOrder(UUID uuid, OrderStatusRequestDTO newStatusOrder) throws Exception {

        Order order = orderRepository.findById(uuid).orElse(null);
        StatusOrderEnum newStatus = StatusOrderEnum.getById(newStatusOrder.getStatus_id());
        if(ObjectUtils.isEmpty(order)){
            throw new Exception("Nenhum pedido encontrado pelo id: " + uuid);
        }
        order.setStatusId(newStatus.getId());

        orderRepository.save(order);

    }
}
