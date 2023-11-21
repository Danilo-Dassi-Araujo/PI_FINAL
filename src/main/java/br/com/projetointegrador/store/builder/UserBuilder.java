package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.request.ClientRequestDTO;
import br.com.projetointegrador.store.dto.request.UserRequestDTO;
import br.com.projetointegrador.store.dto.response.RegisterDTOResponse;
import br.com.projetointegrador.store.enums.UserRole;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.model.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class UserBuilder {

    public static User buildFrom(RegisterDTOResponse response, String passwordEncripted){
        return User
                .builder()
                .email(response.getEmail())
                .password(passwordEncripted)
                .name(response.getName())
                .cpf(response.getCpf())
                .role(response.getGroup())
                .isActive(Boolean.TRUE)
                .build();
    }

    public static User buildFrom(User user){
        return User
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .name(user.getName())
                .role(user.getRole())
                .cpf(user.getCpf())
                .password(user.getPassword())
                .isActive(user.getIsActive() ? Boolean.FALSE : Boolean.TRUE)
                .build();
    }

    public static User buildFrom(UserRequestDTO request, String email, String cpf, String passwordEncripted, User user){
        return User
                .builder()
                .id(user.getId())
                .email(email)
                .name(request.getName())
                .role(request.getRole())
                .cpf(cpf)
                .password(passwordEncripted)
                .isActive(Boolean.TRUE)
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User buildFrom(ClientRequestDTO request){
        return User
                .builder()
                .email(request.getEmail())
                .name(request.getFullname())
                .role(UserRole.CLIENT)
                .cpf(request.getCpf())
                .password(request.getPassword())
                .isActive(Boolean.TRUE)
                .createdAt(LocalDate.now())
                .build();
    }
    public static User buildFrom(Client request){
        return User
                .builder()
                .id(request.getId())
                .email(request.getEmail())
                .name(request.getNomeCompleto())
                .role(UserRole.CLIENT)
                .cpf(request.getCpf())
                .password(request.getSenha())
                .isActive(Boolean.TRUE)
                .createdAt(LocalDate.now())
                .build();
    }
}