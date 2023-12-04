package br.com.projetointegrador.store.service.user;

import br.com.projetointegrador.store.dto.response.order.UserResponseDTO;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserService {

    private final UserRepository userRepository;

    public User getUser(UUID uuid) throws Exception {

        User userToResponse = userRepository.findById(uuid).orElse(null);

        if(ObjectUtils.isEmpty(userToResponse)){
            throw new Exception("Nenhum usuário encontrado pelo id: " + uuid);
        }

        return User
                .builder()
                .id(userToResponse.getId())
                .name(userToResponse.getName())
                .role(userToResponse.getRole())
                .cpf(userToResponse.getCpf())
                .email(userToResponse.getEmail())
                .build();
    }

}
