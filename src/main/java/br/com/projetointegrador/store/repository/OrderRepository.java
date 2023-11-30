package br.com.projetointegrador.store.repository;

import br.com.projetointegrador.store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Order findByOrderCode(String orderCode);

}
