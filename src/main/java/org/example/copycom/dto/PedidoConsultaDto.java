package org.example.copycom.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PedidoConsultaDto {
    private Long id;
    private String serieNumero;
    private Boolean estadoProceso;
    private Date fechaPedido;
}
