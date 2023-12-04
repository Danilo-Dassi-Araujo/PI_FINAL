package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.dto.request.order.OrderRequestDTO;
import br.com.projetointegrador.store.dto.response.order.*;
import br.com.projetointegrador.store.model.Order;
import br.com.projetointegrador.store.service.order.*;
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
    private final ListingAllOrdersService listingAllOrdersService;
    private final ChangeStatusOrderService changeStatusOrderService;
    private final GetOrderStatusService getOrderStatusService;

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

    @GetMapping("/listingAllOrders")
    public List<AllMyOrdersResponseDTO> listingAllOrders(){
        return listingAllOrdersService.listingAllOrders();
    }

    @GetMapping("/getOrderStatus/{uuid}")
    public StatusOrderIdResponseDTO getOrderStatus(@PathVariable UUID uuid) throws Exception {
        return getOrderStatusService.getOrderStatus(uuid);
    }

    @PutMapping("/changeStatusOrder/{uuid}")
    public void changeStatusOrder(@PathVariable UUID uuid, @RequestBody OrderStatusRequestDTO status_id) throws Exception {
       changeStatusOrderService.changeStatusOrder(uuid,status_id);
    }

}