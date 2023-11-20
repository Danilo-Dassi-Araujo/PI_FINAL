package br.com.projetointegrador.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enderecos", schema = "PUBLIC")
public class Address {

    @Id
    @Column(name = "id")
    private UUID idEndereco;

    @Column(name = "cep")
    private String cep;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "uf")
    private String uf;

    @Column(name = "isDefault")
    private Boolean isDefault;

    @Column(name = "typeAddress")
    private String typeAddress;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Client client;

    @PrePersist
    private void beforePersist() {
        this.idEndereco = UUID.randomUUID();
    }
}
