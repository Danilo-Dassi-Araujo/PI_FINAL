package br.com.projetointegrador.store.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imagens", schema = "PUBLIC")
public class Image {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_image",insertable=false, updatable=false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    private byte[] dados;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Product productId;
}