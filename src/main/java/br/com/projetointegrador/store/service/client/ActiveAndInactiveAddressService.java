package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActiveAndInactiveAddressService {

    private final AddressRepository addressRepository;

    public void activeOrInactiveAddress(UUID id) throws Exception {

        if (ObjectUtils.isEmpty(id)) {
            throw new Exception("ID está vazio!");
        }

        Address addressToChange = addressRepository.findById(id).orElse(null);

        assert addressToChange != null;
        addressToChange.setIsActive(!addressToChange.getIsActive());

        addressRepository.save(addressToChange);

    }
}
