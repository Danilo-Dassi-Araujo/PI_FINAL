package br.com.projetointegrador.store.service.user;

import br.com.projetointegrador.store.dto.response.ListingDTOResponse;
import br.com.projetointegrador.store.enums.UserRole;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.UserRepository;
import br.com.projetointegrador.store.repository.specification.UserSpecification;
import br.com.projetointegrador.store.specification.ControllerFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ListingUsersService {

    private final UserRepository userRepository;
    private final UserSpecification userSpecification;

    public List<ListingDTOResponse> getAllUsers(ControllerFilter controllerFilter, String role) throws Exception {
        if (ObjectUtils.isEmpty(controllerFilter.getName())) {
            return listAllUsers(role);
        }

        if (UserRole.STOCKIST.getName().equals(role)) {
            throw new Exception("Você não tem permissão para listar usuários");
        }

        List<User> byUsers = userSpecification.findBy(controllerFilter);
        List<ListingDTOResponse> usersList = new ArrayList<>();

        for (User user : byUsers) {
            ListingDTOResponse build = ListingDTOResponse
                    .builder()
                    .id(user.getId())
                    .cpf(user.getCpf())
                    .name(user.getName())
                    .email(user.getEmail())
                    .groupIndicator(user.getRole().getName())
                    .isActive(user.getIsActive())
                    .build();
            if(!UserRole.CLIENT.getName().equals(build.getName())){
                usersList.add(build);
            }
        }
        return usersList;
    }

    private List<ListingDTOResponse> listAllUsers(String role) throws Exception {
        if (UserRole.STOCKIST.getName().equals(role)) {
            throw new Exception("Você não tem permissão para listar usuários");
        }

        List<User> allUsers = userRepository.findAll();
        List<ListingDTOResponse> usersList = new ArrayList<>();

        for (User user : allUsers) {
            ListingDTOResponse build = ListingDTOResponse
                    .builder()
                    .id(user.getId())
                    .cpf(user.getCpf())
                    .name(user.getName())
                    .email(user.getEmail())
                    .groupIndicator(user.getRole().getName())
                    .isActive(user.getIsActive())
                    .build();
            if(!UserRole.CLIENT.getName().equals(build.getGroupIndicator())){
                usersList.add(build);
            }
        }
        return usersList;
    }
}
