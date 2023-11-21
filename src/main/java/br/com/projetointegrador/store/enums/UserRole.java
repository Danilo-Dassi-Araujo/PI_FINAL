package br.com.projetointegrador.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("Administrador", 1),
    STOCKIST("Estoquista",2),
    CLIENT("Cliente",3);

    private String name;
    private Integer id;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_".concat(this.name())));
    }

}
