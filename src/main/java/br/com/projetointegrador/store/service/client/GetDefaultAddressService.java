package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDefaultAddressService {

    private final AddressRepository addressRepository;

    public Address getDefaultAddress(UUID uuid) throws Exception {
        List<Address> addresses = addressRepository.findAllByClientId(uuid);

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
