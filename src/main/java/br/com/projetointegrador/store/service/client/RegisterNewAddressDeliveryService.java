package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.dto.request.DeliveryAddressRequestDTO;
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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterNewAddressDeliveryService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    public void registerNewAddress(DeliveryAddressRequestDTO deliveryAddressRequestDTO, UUID id) throws Exception {

        User usuarioLogado = userRepository.findById(id).orElse(null);

        UUID idClienteLogado;
        if(!ObjectUtils.isEmpty(usuarioLogado)){
            String email = usuarioLogado.getEmail();
            Client clientByEmail = clientRepository.findByEmail(email);
            idClienteLogado = clientByEmail.getId();
        } else {
            idClienteLogado = null;
        }

        if(ObjectUtils.isEmpty(idClienteLogado)){
            throw new Exception("Nenhum cliente encontrado!");
        }

        Client client = clientRepository.findById(idClienteLogado).orElse(null);

        if (!ObjectUtils.isEmpty(client)) {
            Address build = Address
                    .builder()
                    .cep(deliveryAddressRequestDTO.getCep())
                    .cidade(deliveryAddressRequestDTO.getCity())
                    .uf(deliveryAddressRequestDTO.getUf())
                    .numero(deliveryAddressRequestDTO.getNumber())
                    .isDefault(Boolean.FALSE)
                    .isActive(Boolean.TRUE)
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