package br.com.borsoitech.pdv.controller;

import br.com.borsoitech.pdv.controller.exception.StandardError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.borsoitech.pdv.entity.Cliente;
import br.com.borsoitech.pdv.service.ClienteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cliente", description = "API para gerenciamento de cliente")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServico clienteServico;
    
    public ClienteController(ClienteServico clienteServico) {
        this.clienteServico = clienteServico;
    }

    @Operation(summary = "Obter todos os clientes", description = "Retorna uma página de todos os clientes")
    @ApiResponse(responseCode = "200", description = "Clientes encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cliente.class))))
    @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    @PreAuthorize("hasAnyRole('ADMIN', 'ROLE_OPERATOR')")
    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllPaged(@RequestParam(name = "nome", defaultValue = "") String nome, Pageable pageable) {
        return ResponseEntity.ok().body(clienteServico.acharTodosPaginado(nome, pageable));
    }
}
