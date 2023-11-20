package br.com.projetointegrador.store.service.info;

import br.com.projetointegrador.store.enums.AddressTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressTypeInfoService {

    public List<String> getTypeAddressList() {
        List<String> addressTypeList = new ArrayList<>();

        Arrays.stream(AddressTypeEnum.values())
                .toList()
                .forEach(e -> addressTypeList.add(e.name()));

        return addressTypeList;
    }
}