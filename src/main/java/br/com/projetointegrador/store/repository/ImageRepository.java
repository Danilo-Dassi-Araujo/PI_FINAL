package br.com.projetointegrador.store.repository;

import br.com.projetointegrador.store.model.Image;
import br.com.projetointegrador.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findAllByProductId(Product product);
}
