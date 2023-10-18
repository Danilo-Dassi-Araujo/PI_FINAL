package br.com.projetointegrador.store.service;

import br.com.projetointegrador.store.controller.dto.request.UserRequestDTO;
import br.com.projetointegrador.store.controller.dto.response.UserResponseDTO;
import br.com.projetointegrador.store.enums.UserRole;
import br.com.projetointegrador.store.exceptions.UserNotFoundException;
import br.com.projetointegrador.store.model.User;
import br.com.projetointegrador.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException());
    }

    public UserResponseDTO findById(UUID id) {
        var user = this.getById(id);

        return this.modelMapper.map(user, UserResponseDTO.class);
    }

    public List<UserResponseDTO> findAll() {
        return this.userRepository.findAll().stream().map(user -> this.modelMapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());
    }

    public UserResponseDTO save(UserRequestDTO request) {
        var user = this.modelMapper.map(request, User.class);

        user.setRole(UserRole.CLIENT);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);

        return this.modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO update(UUID id, UserRequestDTO request) {
        var user = this.getById(id);

        BeanUtils.copyProperties(request, user, "password");

        if (StringUtils.hasText(request.getPassword())) {
            var encodedPassword = this.passwordEncoder.encode(request.getPassword());

            user.setPassword(encodedPassword);
        }

        this.userRepository.save(user);

        return this.modelMapper.map(user, UserResponseDTO.class);
    }

    public void deleteById(UUID id) {
        var user = this.getById(id);

        this.userRepository.delete(user);
    }

    private User getById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

}
