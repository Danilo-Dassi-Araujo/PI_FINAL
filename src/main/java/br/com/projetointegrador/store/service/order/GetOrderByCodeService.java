package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.model.Order;
import br.com.projetointegrador.store.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrderByCodeService {

    private final OrderRepository orderRepository;

    public Order getByOrderCode(String orderCode) {

        return orderRepository.findByOrderCode(orderCode);
    }

}
