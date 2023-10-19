package br.com.projetointegrador.store.service.user;

import br.com.projetointegrador.store.builder.RegisterDTOResponseBuilder;
import br.com.projetointegrador.store.builder.UserBuilder;
import br.com.projetointegrador.store.dto.request.UserRequestDTO;
import br.com.projetointegrador.store.dto.response.RegisterDTOResponse;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.UserRepository;
import br.com.projetointegrador.store.utils.ValidaCpf;
import br.com.projetointegrador.store.utils.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class RegisterUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterDTOResponse registerUser(UserRequestDTO userRequestDTO) throws Exception {
        ValidatorUtils.validateRequest(userRequestDTO);
        boolean cpf = ValidaCpf.isCPF(userRequestDTO.getCpf());

        if (Boolean.FALSE.equals(cpf)) {
            throw new Exception("CPF inválido");
        }

        User emailValidate = userRepository.findByEmail(userRequestDTO.getEmail()).orElse(null);
        User cpfValidate = userRepository.findByCpf(userRequestDTO.getCpf());

        if (!ObjectUtils.isEmpty(emailValidate)) {
            throw new Exception("Email já cadastrado!");
        }

        if (!ObjectUtils.isEmpty(cpfValidate)) {
            throw new Exception("CPF já cadastrado!");
        }

        RegisterDTOResponse registerDTOResponse = RegisterDTOResponseBuilder.buildFrom(userRequestDTO);
        String passwordEncripted = passwordEncoder.encode(userRequestDTO.getPassword());
        User toSave = UserBuilder.buildFrom(registerDTOResponse, passwordEncripted);
        userRepository.save(toSave);
        return registerDTOResponse;
    }
}