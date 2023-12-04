package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.dto.response.ClientEditResponse;
import br.com.projetointegrador.store.enums.GenderEnum;
import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.AddressRepository;
import br.com.projetointegrador.store.repository.ClientRepository;
import br.com.projetointegrador.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetClientByIdService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    public ClientEditResponse getClient(UUID uuid) throws Exception {

        User usuarioLogado = userRepository.findById(uuid).orElse(null);

        UUID idClienteLogado;
        if(!ObjectUtils.isEmpty(usuarioLogado)){
            String email = usuarioLogado.getEmail();
            Client clientByEmail = clientRepository.findByEmail(email);
            idClienteLogado = clientByEmail.getId();
        } else {
            idClienteLogado = null;
        }

        assert idClienteLogado != null;
        Client clientLogged = clientRepository.findById(idClienteLogado).orElse(null);

        if(ObjectUtils.isEmpty(clientLogged)){
            throw new Exception("Nenhum usuário encontrado!");
        }
        List<Address> addresses = addressRepository.findAllByClientId(clientLogged.getId());

        addresses.forEach(address -> {
            address.setClient(null);
        });

        String generoId = GenderEnum.getByName(clientLogged.getGenero());

        return ClientEditResponse
                .builder()
                .id(clientLogged.getId())
                .nomeCompleto(clientLogged.getNomeCompleto())
                .dataNascimento(clientLogged.getDataNascimento())
                .generoId(generoId)
                .addresses(addresses)
                .build();
    }
}
