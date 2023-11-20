package br.com.projetointegrador.store.service.client;

import br.com.projetointegrador.store.builder.AddressBuilder;
import br.com.projetointegrador.store.builder.ClientBuilder;
import br.com.projetointegrador.store.builder.RegisterClientDTOResponseBuilder;
import br.com.projetointegrador.store.dto.request.ClientRequestDTO;
import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.model.Client;
import br.com.projetointegrador.store.repository.AddressRepository;
import br.com.projetointegrador.store.repository.ClientRepository;
import br.com.projetointegrador.store.utils.GenderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RegisterClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenderUtils getGenderService;

    public void registerClient(ClientRequestDTO clientRequestDTO) throws Exception {

        Client emailValidate = clientRepository.findByEmail(clientRequestDTO.getEmail());

        if (!ObjectUtils.isEmpty(emailValidate)) {
            throw new Exception("Email já cadastrado!");
        }

        RegisterClientDTOResponseBuilder.buildFrom(clientRequestDTO);
        String passwordEncrypted = passwordEncoder.encode(clientRequestDTO.getPassword());

        String gender = getGenderService.getGender(clientRequestDTO.getGender_id());

        Client toSave = ClientBuilder.buildFrom(clientRequestDTO, passwordEncrypted, gender);
        Client savedClient = clientRepository.save(toSave);

        Address toSaveAddress = AddressBuilder.buildFrom(clientRequestDTO, savedClient);
        addressRepository.save(toSaveAddress);

        if (!ObjectUtils.isEmpty(clientRequestDTO.getDelivery_address())) {
            List<Address> addressesToSave = AddressBuilder.buildListAddress(clientRequestDTO, savedClient);
            addressRepository.saveAll(addressesToSave);
        }
    }
}