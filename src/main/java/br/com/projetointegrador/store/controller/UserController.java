package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.controller.dto.request.UserRequestDTO;
import br.com.projetointegrador.store.controller.dto.response.UserResponseDTO;
import br.com.projetointegrador.store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO save(@Valid @RequestBody UserRequestDTO request) {
        return this.userService.save(request);
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable UUID id) {
        return this.userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable UUID id, @RequestBody UserRequestDTO request) {
        return this.userService.update(id, request);
    }

    @GetMapping
    public List<UserResponseDTO> findAll() {
        return this.userService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        this.userService.deleteById(id);
    }

}
