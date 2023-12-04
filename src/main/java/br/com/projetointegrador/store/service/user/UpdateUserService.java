package br.com.projetointegrador.store.service.user;

import br.com.projetointegrador.store.builder.UpdateDTOResponseBuilder;
import br.com.projetointegrador.store.builder.UserBuilder;
import br.com.projetointegrador.store.dto.request.UserRequestDTO;
import br.com.projetointegrador.store.dto.response.UpdateDTOResponse;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class UpdateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdateDTOResponse updateUser(UserRequestDTO userRequestDTO) throws Exception {

        User user = userRepository.findById(userRequestDTO.getId()).orElse(null);

        if (ObjectUtils.isEmpty(user)) {
            throw new Exception("Usuário não encontrado");
        }

        String passwordEncripted = null;
        if(!ObjectUtils.isEmpty(userRequestDTO.getPassword())){
            passwordEncripted = passwordEncoder.encode(userRequestDTO.getPassword());
        }
        User toSave = UserBuilder.buildFrom(userRequestDTO, user.getEmail(), user.getCpf(), passwordEncripted, user);


        userRepository.save(toSave);

        return UpdateDTOResponseBuilder.buildFrom(toSave);
    }
}