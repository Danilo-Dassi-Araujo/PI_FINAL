package br.com.projetointegrador.store.service.user;

import br.com.projetointegrador.store.builder.UserBuilder;
import br.com.projetointegrador.store.dto.request.IdDTORequest;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.UserRepository;
import br.com.projetointegrador.store.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class InactiveAndActiveUserService {

    private final UserRepository userRepository;

    public void inactiveOrActiveUser(IdDTORequest id) throws Exception {
        ValidatorUtils.validateEmailRequest(id);
        User user = userRepository.findById(id.getId()).orElse(null);

        if(ObjectUtils.isEmpty(user)){
            throw new Exception("Usuário não encontrado");
        }

        User toSave = UserBuilder.buildFrom(user);
        userRepository.save(toSave);
    }
}