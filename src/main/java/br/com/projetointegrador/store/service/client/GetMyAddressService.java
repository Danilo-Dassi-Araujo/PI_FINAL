package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetMyAddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAddress(UUID uuid) throws Exception {

        List<Address> listAddress = addressRepository.findAllByClientId(uuid);

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

            if (address.getIsActive()) {
                addressList.add(addressBuild);
            }
        }
        return addressList;
    }

}