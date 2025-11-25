package org.example.copycom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @Column(name = "numero_pedido", length = 50)
    private String numeroPedido;

    @Column(name = "estado_pedido", length = 20)
    private String estadoPedido; // PENDIENTE, EN_PROCESO, COMPLETADO, CANCELADO

    @Column(name = "prioridad", length = 20)
    private String prioridad; // NORMAL, URGENTE, MUY_URGENTE

    @Column(name = "subtotal", length = 50)
    private String subtotal;

    @Column(name = "descuento", length = 50)
    private String descuento;

    @Column(name = "total", length = 50)
    private String total;

    @Column(name = "id_cliente", length = 50)
    private String idCliente;

    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;

    @Column(name = "fecha_entrega_estimada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntregaEstimada;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;

    @PrePersist
    protected void onCreate() {
        creadoEn = new Date();
        actualizadoEn = new Date();
        if (fechaPedido == null) {
            fechaPedido = new Date();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        actualizadoEn = new Date();
    }
}
