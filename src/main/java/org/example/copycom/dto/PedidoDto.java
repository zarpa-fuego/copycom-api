package org.example.copycom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {
    private Long id;
    private String numeroPedido;
    private String estadoPedido;
    private String prioridad;
    private String subtotal;
    private String descuento;
    private String total;
    private String idCliente;
    private Date fechaPedido;
    private Date fechaEntregaEstimada;
    private String observaciones;
    private Date creadoEn;
    private Date actualizadoEn;
}

