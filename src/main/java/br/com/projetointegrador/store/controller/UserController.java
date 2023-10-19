package br.com.projetointegrador.store.controller;

import br.com.projetointegrador.store.controller.dto.response.UserResponseDTO;
import br.com.projetointegrador.store.dto.request.EmailDTORequest;
import br.com.projetointegrador.store.dto.request.UserRequestDTO;
import br.com.projetointegrador.store.dto.response.ListingDTOResponse;
import br.com.projetointegrador.store.dto.response.RegisterDTOResponse;
import br.com.projetointegrador.store.dto.response.UpdateDTOResponse;
import br.com.projetointegrador.store.service.UserService;
import br.com.projetointegrador.store.service.user.InactiveAndActiveUserService;
import br.com.projetointegrador.store.service.user.ListingUsersService;
import br.com.projetointegrador.store.service.user.RegisterUserService;
import br.com.projetointegrador.store.service.user.UpdateUserService;
import br.com.projetointegrador.store.specification.ControllerFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ListingUsersService listingUsersService;
    private final UserService userService;
    private final RegisterUserService registerUserService;
    private final InactiveAndActiveUserService inactiveAndActiveUserService;
    private final UpdateUserService updateUserService;

    @GetMapping("/listingUser")
    public ResponseEntity<List<ListingDTOResponse>> getFilteredUsers(ControllerFilter controllerFilter) throws Exception {
        List<ListingDTOResponse> allUsers = listingUsersService.getAllUsers(controllerFilter, controllerFilter.getRole());
        return ResponseEntity.ok().body(allUsers);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTOResponse> registerUser(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
        RegisterDTOResponse registerDTOResponse = registerUserService.registerUser(userRequestDTO);
        return ResponseEntity.ok().body(registerDTOResponse);
    }

    @PutMapping
    public ResponseEntity<Void> inactiveOrActiveUser(@RequestBody EmailDTORequest email) throws Exception {
        inactiveAndActiveUserService.inactiveOrActiveUser(email);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UpdateDTOResponse> alterUser(@RequestBody UserRequestDTO requestDTO) throws Exception {
        UpdateDTOResponse updateDTOResponse = updateUserService.updateUser(requestDTO);
        return ResponseEntity.ok().body(updateDTOResponse);
    }
}
