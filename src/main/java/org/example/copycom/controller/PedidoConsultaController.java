package org.example.copycom.controller;

import org.example.copycom.dto.PedidoConsultaDto;
import org.example.copycom.service.PedidoConsultaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido-consulta")
public class PedidoConsultaController {
    private PedidoConsultaService pedidoConsultaService;

    public PedidoConsultaController(PedidoConsultaService pedidoConsultaService) {
        this.pedidoConsultaService = pedidoConsultaService;
    }

    @GetMapping
    public PedidoConsultaDto getPedidoConsultaDtoBySerieNumero(@RequestParam String serieNumero) {
        System.out.println("getPedidoConsultaDtoBySerieNumero");
        return this.pedidoConsultaService.getPedidoReportDtoBySerieNumero(serieNumero);
    }
}
