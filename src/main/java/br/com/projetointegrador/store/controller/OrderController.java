package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.order.OrderRequestDTO;
import br.com.projetointegrador.store.dto.response.order.AllMyOrdersResponseDTO;
import br.com.projetointegrador.store.dto.response.order.OrderResponseCodeDTO;
import br.com.projetointegrador.store.dto.response.order.OrderStringResponseDTO;
import br.com.projetointegrador.store.service.order.GetOrderByCodeService;
import br.com.projetointegrador.store.service.order.ListingMyOrdersService;
import br.com.projetointegrador.store.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ListingMyOrdersService listingMyOrdersService;
    private final GetOrderByCodeService getOrderByCodeService;

    @PostMapping("/register")
    public OrderStringResponseDTO registerOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws Exception {
        return orderService.registerOrder(orderRequestDTO);
    }

    @GetMapping("/myOrders/{uuid}")
    public List<AllMyOrdersResponseDTO> listingMyOrders(@PathVariable UUID uuid) {
        return listingMyOrdersService.getAllMyOrders(uuid);
    }

    @GetMapping("/orderCode/{orderCode}")
    public OrderResponseCodeDTO listingOrderbyCode(@PathVariable String orderCode) {
        return getOrderByCodeService.getByOrderCode(orderCode);
    }
}