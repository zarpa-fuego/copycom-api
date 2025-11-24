package org.example.copycom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serie_numero")
    private String serieNumero;
    @Column(name = "estado_proceso")
    private Boolean estadoProceso; // Si ya esta listo para entregar
    @Column(name = "fecha_pedido")
    private Date fechaPedido;
}
