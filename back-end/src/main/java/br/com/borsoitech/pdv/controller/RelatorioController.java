package br.com.borsoitech.pdv.controller;

import br.com.borsoitech.pdv.entity.Produto;
import br.com.borsoitech.pdv.service.ProdutoServico;
import br.com.borsoitech.pdv.service.RelatorioServico;
import br.com.borsoitech.pdv.service.dbconnection.DBConnection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Relatório", description = "API para gerenciamento de relatório")
@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioServico relatorioService;

    public RelatorioController(RelatorioServico relatorioService) {
        this.relatorioService = relatorioService;
    }

    @Operation(summary = "Obter comprovante de venda", description = "Retorna o comprovante de venda")
    @ApiResponse(responseCode = "200", description = "Comprovante gerado", content = @Content(mediaType = "application/pdf"))
    @PreAuthorize("hasAnyRole('ADMIN', 'ROLE_OPERATOR')")
    @GetMapping("/{id_venda}")
    public ResponseEntity<InputStreamResource> gerarComprovante(@Parameter(description = "ID da venda") @PathVariable("id_venda") Long id_venda) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ID_VENDA", id_venda);

        byte[] pdfBytes = relatorioService.gerarComprovante(parametros, "/relatorio/venda_comprovante.jrxml");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=comprovante.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteArrayInputStream));
    }
}
