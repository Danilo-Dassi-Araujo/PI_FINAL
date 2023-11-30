package br.com.projetointegrador.store.controller;


import br.com.projetointegrador.store.dto.response.ShippingsInfoResponseDTO;
import br.com.projetointegrador.store.dto.response.StatusOrderInfoResponseDTO;
import br.com.projetointegrador.store.dto.response.order.PaymentMethodDTO;
import br.com.projetointegrador.store.service.info.PaymentsMethodsinfoService;
import br.com.projetointegrador.store.service.info.ShippingsInfoService;
import br.com.projetointegrador.store.service.info.StatusOrderInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final StatusOrderInfoService statusOrderInfoService;
    private final PaymentsMethodsinfoService paymentsMethodsinfoService;
    private final ShippingsInfoService shippingsInfoService;

    @GetMapping("/statusOrder")
    public List<StatusOrderInfoResponseDTO> getStatusOrder() {
        return statusOrderInfoService.getStatusOrderList();
    }

    @GetMapping("/shippings")
    public List<ShippingsInfoResponseDTO> getShippings() {
        return shippingsInfoService.getShippings();
    }

    @GetMapping("/paymentsMethods")
    public List<PaymentMethodDTO> getPaymentsMethods() {
        return paymentsMethodsinfoService.getPaymentsMethods();
    }

}