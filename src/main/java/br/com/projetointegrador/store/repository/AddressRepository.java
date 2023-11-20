package br.com.projetointegrador.store.repository;

import br.com.projetointegrador.store.model.Address;
import br.com.projetointegrador.store.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
