package org.example.copycom.repository;

import org.example.copycom.entity.EnvioEntity;
import org.example.copycom.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<EnvioEntity, Long> {
    Optional<EnvioEntity> findByPedido(PedidoEntity pedido);
    Optional<EnvioEntity> findByNumeroGuia(String numeroGuia);
    boolean existsByNumeroGuia(String numeroGuia);
}

