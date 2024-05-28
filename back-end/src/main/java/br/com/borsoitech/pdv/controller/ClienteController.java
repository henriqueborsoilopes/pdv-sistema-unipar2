package br.com.borsoitech.pdv.controller;

import org.springdoc.api.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.borsoitech.pdv.entity.Cliente;
import br.com.borsoitech.pdv.service.ClienteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Clientes", description = "Contém todos os recursos relacionados com cliente, como: página de clientes.")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServico clienteServico;
    
    public ClienteController(ClienteServico clienteServico) {
        this.clienteServico = clienteServico;
    }
    
    @Operation(summary = "Página de clientes",
            description = "Recurso para listar clientes paginado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(mediaType = "Application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllPaged(@RequestParam(name = "nome", defaultValue = "") String nome, Pageable pageable) {
        return ResponseEntity.ok().body(clienteServico.acharTodosPaginado(pageable));
    }
}
