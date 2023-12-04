package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.request.ClientRequestDTO;
import br.com.projetointegrador.store.dto.request.DeliveryAddressRequestDTO;
import br.com.projetointegrador.store.enums.AddressTypeEnum;
import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AddressBuilder {

    public static Address buildFrom(ClientRequestDTO clientRequestDTO, Client client) {

        if (ObjectUtils.isEmpty(clientRequestDTO.getBilling_address().getIsDefault())) {
            clientRequestDTO.getBilling_address().setIsDefault(false);
        }

        return Address
                .builder()
                .cep(clientRequestDTO.getBilling_address().getCep())
                .uf(clientRequestDTO.getBilling_address().getUf())
                .bairro(clientRequestDTO.getBilling_address().getNeighborhood())
                .cidade(clientRequestDTO.getBilling_address().getCity())
                .client(client)
                .complemento(clientRequestDTO.getBilling_address().getComplement())
                .numero(clientRequestDTO.getBilling_address().getNumber())
                .logradouro(clientRequestDTO.getBilling_address().getStreet())
                .isDefault(clientRequestDTO.getBilling_address().getIsDefault())
                .typeAddress(AddressTypeEnum.FATURAMENTO.name())
                .isActive(Boolean.TRUE)
                .build();
    }

    public static List<Address> buildListAddress(ClientRequestDTO clientRequestDTO, Client client) {

        List<Address> addressDTOList = new ArrayList<>();

        for (DeliveryAddressRequestDTO deliveryAddressRequestDTO : clientRequestDTO.getDelivery_address()) {
            Address build = Address
                    .builder()
                    .cep(deliveryAddressRequestDTO.getCep())
                    .uf(deliveryAddressRequestDTO.getUf())
                    .bairro(deliveryAddressRequestDTO.getNeighborhood())
                    .cidade(deliveryAddressRequestDTO.getCity())
                    .client(client)
                    .complemento(deliveryAddressRequestDTO.getComplement())
                    .numero(deliveryAddressRequestDTO.getNumber())
                    .logradouro(deliveryAddressRequestDTO.getStreet())
                    .isDefault(deliveryAddressRequestDTO.getIsDefault())
                    .typeAddress(AddressTypeEnum.ENTREGA.name())
                    .isActive(Boolean.TRUE)
                    .build();
            addressDTOList.add(build);
        }
        return addressDTOList;
    }
}