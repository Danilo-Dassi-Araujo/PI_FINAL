package br.com.projetointegrador.store.repository;

import br.com.projetointegrador.store.model.CardPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardPaymentsRepository extends JpaRepository<CardPayments, UUID> {
}