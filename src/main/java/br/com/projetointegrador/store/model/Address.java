package br.com.projetointegrador.store.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ENDERECO", schema = "PUBLIC")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco",insertable = false, updatable = false)
    private Integer idEndereco;

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

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Client client;
}
