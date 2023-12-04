package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.enums.AddressTypeEnum;
import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.AddressRepository;
import br.com.projetointegrador.store.repository.ClientRepository;
import br.com.projetointegrador.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetMyAddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public List<Address> getAddress(UUID uuid) throws Exception {

        User usuarioLogado = userRepository.findById(uuid).orElse(null);

        UUID idClienteLogado = null;
        if(!ObjectUtils.isEmpty(usuarioLogado)){
            String email = usuarioLogado.getEmail();
            Client clientByEmail = clientRepository.findByEmail(email);
            idClienteLogado = clientByEmail.getId();
        }


        List<Address> listAddress = addressRepository.findAllByClientId(idClienteLogado);

        if (listAddress.isEmpty()) {
            throw new Exception("Nenhum endereço encontrado pelo id de cliente: " + uuid);
        }

        List<Address> addressList = new ArrayList<>();

        for (Address address : listAddress) {

            address.getClient().setSenha("Tem senha nenhuma não filho da puta");

            Address addressBuild = Address
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

            if (address.getIsActive() && !AddressTypeEnum.FATURAMENTO.name().equals(address.getTypeAddress())) {
                addressList.add(addressBuild);
            }
        }
        return addressList;
    }

}