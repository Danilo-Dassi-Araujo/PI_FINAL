package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateDefaultAddressService {

    private final AddressRepository addressRepository;

    public void updateDefaultAddress(UUID id, UUID idAddress) throws Exception {

        if(ObjectUtils.isEmpty(id)){
            throw new Exception("ID de cliente está vazio!");
        }
        if(ObjectUtils.isEmpty(idAddress)){
            throw new Exception("ID de endereço está vazio!");
        }

        List<Address> allByIdCliente = addressRepository.findAll();

        List<Address> listAddress = allByIdCliente.stream().filter(address -> id.equals(address.getClient().getId())).toList();

        if(ObjectUtils.isEmpty(listAddress)){
            throw new Exception("Nenhum endereço foi encontrado para o id: " + id);
        }

        listAddress.forEach(e -> e.setIsDefault(false));

        Address newAddressDefault = addressRepository.findById(idAddress).orElse(null);

        if(ObjectUtils.isEmpty(newAddressDefault)){
            throw new Exception("Nenhum endereço foi encontrado para o id: " + idAddress);
        }
        newAddressDefault.setIsDefault(true);
        addressRepository.save(newAddressDefault);
    }
}
