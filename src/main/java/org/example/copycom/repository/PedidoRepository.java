package org.example.copycom.repository;

import org.example.copycom.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    Optional<PedidoEntity> findByNumeroPedido(String numeroPedido);
    boolean existsByNumeroPedido(String numeroPedido);
}

