package br.com.projetointegrador.store.repository;

import br.com.projetointegrador.store.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {

    List<OrderProduct> findAllByOrderId(UUID uuid);
    List<OrderProduct> findAllByOrderOrderCode(String code);
}
