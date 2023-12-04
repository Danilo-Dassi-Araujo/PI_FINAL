package br.com.projetointegrador.store.service.client;

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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDefaultAddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public Address getDefaultAddress(UUID uuid) throws Exception {

        User usuarioLogado = userRepository.findById(uuid).orElse(null);

        UUID idClienteLogado = null;
        if(!ObjectUtils.isEmpty(usuarioLogado)){
            String email = usuarioLogado.getEmail();
            Client clientByEmail = clientRepository.findByEmail(email);
            idClienteLogado = clientByEmail.getId();
        }

        List<Address> addresses = addressRepository.findAllByClientId(idClienteLogado);

        if (addresses.isEmpty()) {
            throw new Exception("Nenhum endereço encontrado pelo id de cliente: " + uuid);
        }

        List<Address> listAddress = addresses.stream().filter(address -> Boolean.TRUE.equals(address.getIsDefault())).collect(Collectors.toList());

        Address address = listAddress.get(0);

        return Address
                .builder()
                .cidade(address.getCidade())
                .isDefault(address.getIsDefault())
                .logradouro(address.getLogradouro())
                .complemento(address.getComplemento())
                .numero(address.getNumero())
                .bairro(address.getBairro())
                .cep(address.getCep())
                .typeAddress(address.getTypeAddress())
                .uf(address.getUf())
//                    .client(address.getClient())
                .isActive(address.getIsActive())
                .idEndereco(address.getIdEndereco())
                .build();
    }

}
