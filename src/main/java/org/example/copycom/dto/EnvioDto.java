package org.example.copycom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioDto {
    private Long id;
    private Long idPedido;
    private String direccionOrigen;
    private String direccionDestino;
    private String transportista;
    private String numeroGuia;
    private String estadoEnvio;
    private Date fechaEnvio;
    private Date fechaEstimadaEntrega;
    private Date fechaRealEntrega;
    private String observaciones;
    private Date creadoEn;
    private Date actualizadoEn;
}

