package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.order.OrderRequestDTO;
import br.com.projetointegrador.store.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/register")
    public void registerOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws Exception {
        orderService.registerOrder(orderRequestDTO);
    }
}
