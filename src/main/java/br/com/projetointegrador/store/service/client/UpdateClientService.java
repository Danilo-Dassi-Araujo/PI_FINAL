package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.builder.UserBuilder;
import br.com.projetointegrador.store.dto.request.UpdateClientRequestDTO;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.ClientRepository;
import br.com.projetointegrador.store.repository.UserRepository;
import br.com.projetointegrador.store.utils.GenderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenderUtils getGenderService;

    public void updateClient(UUID id, UpdateClientRequestDTO updateClientRequestDTO) throws Exception {

        if (ObjectUtils.isEmpty(id)) {
            throw new Exception("Id está vazio ou nulo!");
        }

        User usuarioLogado = userRepository.findById(id).orElse(null);

        UUID idClienteLogado;
        if(!ObjectUtils.isEmpty(usuarioLogado)){
            String email = usuarioLogado.getEmail();
            Client clientByEmail = clientRepository.findByEmail(email);
            idClienteLogado = clientByEmail.getId();
        } else {
            idClienteLogado = null;
        }

        assert idClienteLogado != null;


        Client clientToUpdate = clientRepository.findById(idClienteLogado).orElse(null);

        if (ObjectUtils.isEmpty(clientToUpdate)) {
            throw new Exception("Nenhum cliente encontrado!");
        }

        if (ObjectUtils.isEmpty(updateClientRequestDTO)) {
            throw new Exception("Request vazia!");
        }
        String passwordEncoded = null;
        if(!ObjectUtils.isEmpty(updateClientRequestDTO.getPassword())){
            passwordEncoded = passwordEncoder.encode(updateClientRequestDTO.getPassword());
        }
        String gender = getGenderService.getGender(updateClientRequestDTO.getGender_id());

        Client clientToSaveUpdate = Client
                .builder()
                .id(idClienteLogado)
                .email(clientToUpdate.getEmail())
                .cpf(clientToUpdate.getCpf())
                .senha(clientToUpdate.getSenha().equals(passwordEncoded) || ObjectUtils.isEmpty(passwordEncoded) ? clientToUpdate.getSenha() : passwordEncoded)
                .genero(clientToUpdate.getGenero().equals(updateClientRequestDTO.getGender_id()) ? clientToUpdate.getGenero() : gender)
                .nomeCompleto(clientToUpdate.getNomeCompleto().equals(updateClientRequestDTO.getName()) ? clientToUpdate.getNomeCompleto() : updateClientRequestDTO.getName())
                .dataNascimento(clientToUpdate.getDataNascimento().equals(updateClientRequestDTO.getBirth_date()) ? clientToUpdate.getDataNascimento() : updateClientRequestDTO.getBirth_date())
                .build();

        Client saveUpdate = clientRepository.save(clientToSaveUpdate);

        User userToUpdate = UserBuilder.buildFrom(saveUpdate, usuarioLogado.getId());
        userRepository.save(userToUpdate);
    }
}