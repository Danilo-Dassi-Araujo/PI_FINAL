package br.com.projetointegrador.store.controller;


import br.com.projetointegrador.store.service.info.AddressTypeInfoService;
import br.com.projetointegrador.store.service.info.PaymentsMethodsinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final AddressTypeInfoService addressTypeInfoService;
    private final PaymentsMethodsinfoService paymentsMethodsinfoService;

    @GetMapping("/addressType")
    public List<String> getAddressType() {
        return addressTypeInfoService.getTypeAddressList();
    }

    @GetMapping("/paymentsMethods")
    public List<String> getPaymentsMethods(){
        return paymentsMethodsinfoService.getPaymentsMethods();
    }

}
