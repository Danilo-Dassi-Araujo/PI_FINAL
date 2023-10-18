package br.com.projetointegrador.store.model;

import br.com.projetointegrador.store.enums.UserRole;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@StaticMetamodel(User.class)
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public class User_ {

    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, UUID> id;
    public static volatile SingularAttribute<User, UserRole> role;
    public static volatile SingularAttribute<User, String> cpf;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Boolean> isActive;
    public static volatile SingularAttribute<User, LocalDate> createdAt;
    public static volatile SingularAttribute<User, LocalDateTime> updatedAt;
}