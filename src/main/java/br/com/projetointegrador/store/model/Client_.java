package br.com.projetointegrador.store.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

import java.time.LocalDate;

@StaticMetamodel(Client.class)
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public class Client_ {

    public static volatile SingularAttribute<Client, String> email;
    public static volatile SingularAttribute<Client, String> nomeCompleto;
    public static volatile SingularAttribute<Client, LocalDate> dataNascimento;
    public static volatile SingularAttribute<Client, String> genero;
}
