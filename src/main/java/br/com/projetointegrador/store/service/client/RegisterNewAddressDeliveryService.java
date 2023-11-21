package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.dto.request.DeliveryAddressRequestDTO;
import br.com.projetointegrador.store.enums.AddressTypeEnum;
import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.repository.AddressRepository;
import br.com.projetointegrador.store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterNewAddressDeliveryService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public void registerNewAddress(DeliveryAddressRequestDTO deliveryAddressRequestDTO, UUID id) {

        Client client = clientRepository.findById(id).orElse(null);

        if (!ObjectUtils.isEmpty(client)) {
            Address build = Address
                    .builder()
                    .cep(deliveryAddressRequestDTO.getCep())
                    .cidade(deliveryAddressRequestDTO.getCity())
                    .uf(deliveryAddressRequestDTO.getUf())
                    .numero(deliveryAddressRequestDTO.getNumber())
                    .isDefault(deliveryAddressRequestDTO.getIsDefault())
                    .bairro(deliveryAddressRequestDTO.getNeighborhood())
                    .logradouro(deliveryAddressRequestDTO.getStreet())
                    .complemento(deliveryAddressRequestDTO.getComplement())
                    .client(client)
                    .typeAddress(AddressTypeEnum.ENTREGA.name())
                    .build();

            addressRepository.save(build);
        }
    }
}