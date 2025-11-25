package org.example.copycom.repository;

import org.example.copycom.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoConsultaRepository extends JpaRepository<PedidoEntity, Long> {

     PedidoEntity getPedidoEntityByNumeroPedido(String numeroPedido);
}
