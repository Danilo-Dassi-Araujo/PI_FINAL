package br.com.projetointegrador.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("Administrador"),
    STOCKIST("Estoquista"),
    CLIENT("Cliente");

    private String name;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_".concat(this.name())));
    }

}
