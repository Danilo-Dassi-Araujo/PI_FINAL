package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.builder.ClientBuilder;
import br.com.projetointegrador.store.builder.RegisterClientDTOResponseBuilder;
import br.com.projetointegrador.store.dto.request.ClientRequestDTO;
import br.com.projetointegrador.store.dto.response.RegisterClientDTOResponse;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class RegisterClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterClientDTOResponse registerClient(ClientRequestDTO clientRequestDTO) throws Exception {

        Client emailValidate = clientRepository.findById(clientRequestDTO.getEmail()).orElse(null);

        if (!ObjectUtils.isEmpty(emailValidate)) {
            throw new Exception("Email já cadastrado!");
        }

        RegisterClientDTOResponse registerDTOResponse = RegisterClientDTOResponseBuilder.buildFrom(clientRequestDTO);
        String passwordEncripted = passwordEncoder.encode(clientRequestDTO.getPassword());
        Client toSave = ClientBuilder.buildFrom();
        clientRepository.save(toSave);
        return registerDTOResponse;
    }
}