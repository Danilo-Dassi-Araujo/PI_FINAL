package br.com.projetointegrador.store.builder;

import br.com.projetointegrador.store.dto.request.ClientRequestDTO;
import br.com.projetointegrador.store.dto.request.DeliveryAddressDTO;
import br.com.projetointegrador.store.enums.AddressTypeEnum;
import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.model.Client;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AddressBuilder {

    public static Address buildFrom(ClientRequestDTO clientRequestDTO, Client client) {
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
                .build();
    }

    public static List<Address> buildListAddress(ClientRequestDTO clientRequestDTO, Client client) {

        List<Address> addressDTOList = new ArrayList<>();

        for(DeliveryAddressDTO deliveryAddressDTO: clientRequestDTO.getDelivery_address()){
            Address build = Address
                    .builder()
                    .cep(deliveryAddressDTO.getCep())
                    .uf(deliveryAddressDTO.getUf())
                    .bairro(deliveryAddressDTO.getNeighborhood())
                    .cidade(deliveryAddressDTO.getCity())
                    .client(client)
                    .complemento(deliveryAddressDTO.getComplement())
                    .numero(deliveryAddressDTO.getNumber())
                    .logradouro(deliveryAddressDTO.getStreet())
                    .isDefault(deliveryAddressDTO.getIsDefault())
                    .typeAddress(AddressTypeEnum.ENTREGA.name())
                    .build();
            addressDTOList.add(build);
        }
        return addressDTOList;
    }
}
