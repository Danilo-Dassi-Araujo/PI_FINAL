package br.com.projetointegrador.store.service;

import br.com.projetointegrador.store.controller.dto.request.AuthRequestDTO;
import br.com.projetointegrador.store.controller.dto.response.AuthResponseDTO;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        var user = this.userService.loadUserByUsername(request.getEmail());

        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        this.authenticationManager.authenticate(authenticationToken);

        var jwtToken = this.jwtService.generateToken(user);

        User loggedUser = userRepository.findByEmail(request.getEmail()).orElseThrow();

        return new AuthResponseDTO(jwtToken,loggedUser.getId(), loggedUser.getRole().getName());
    }
}