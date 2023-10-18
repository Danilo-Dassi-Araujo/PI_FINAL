package br.com.projetointegrador.store.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@StaticMetamodel(Product.class)
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public class Product_ {

    public static volatile SingularAttribute<Product, UUID> id;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Double> rate;
    public static volatile SingularAttribute<Product, BigDecimal> price;
    public static volatile SingularAttribute<Product, Integer> stockQuantity;
    public static volatile SingularAttribute<Product, Boolean> isActive;
    public static volatile SingularAttribute<Product, LocalDate> createdAt;
    public static volatile SingularAttribute<Product, LocalDateTime> updatedAt;
}
