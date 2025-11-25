package org.example.copycom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "envios")
@NoArgsConstructor
@AllArgsConstructor
public class EnvioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoEntity pedido;

    @Column(name = "direccion_origen", length = 255)
    private String direccionOrigen;

    @Column(name = "direccion_destino", length = 255)
    private String direccionDestino;

    @Column(name = "transportista", length = 100)
    private String transportista;

    @Column(name = "numero_guia", length = 100)
    private String numeroGuia;

    @Column(name = "estado_envio", length = 20)
    private String estadoEnvio; // EN_PREPARACION, EN_TRANSITO, EN_REPARTO, ENTREGADO, CANCELADO

    @Column(name = "fecha_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @Column(name = "fecha_estimada_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEstimadaEntrega;

    @Column(name = "fecha_real_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRealEntrega;

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
    }

    @PreUpdate
    protected void onUpdate() {
        actualizadoEn = new Date();
    }
}

