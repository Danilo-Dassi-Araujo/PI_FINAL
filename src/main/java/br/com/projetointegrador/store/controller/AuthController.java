package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.controller.dto.request.AuthRequestDTO;
import br.com.projetointegrador.store.controller.dto.response.AuthResponseDTO;
import br.com.projetointegrador.store.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public AuthResponseDTO authenticate(@Valid @RequestBody AuthRequestDTO request) {

        return this.authService.authenticate(request);
    }

}
