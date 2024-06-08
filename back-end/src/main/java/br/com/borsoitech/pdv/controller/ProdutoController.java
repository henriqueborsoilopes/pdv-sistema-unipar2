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

import br.com.borsoitech.pdv.entity.Produto;
import br.com.borsoitech.pdv.service.ProdutoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produto", description = "API para gerenciamento de produto")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private final ProdutoServico produtoService;
    
    public ProdutoController(ProdutoServico produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Obter todos os produtos", description = "Retorna uma página de todos os produtos")
    @ApiResponse(responseCode = "200", description = "Produtos encontrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Produto.class))))
    @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    @PreAuthorize("hasAnyRole('ADMIN', 'ROLE_OPERATOR')")
    @GetMapping
    public ResponseEntity<Page<Produto>> getAllPaged(@RequestParam(name = "descricao", defaultValue = "") String descricao, Pageable pageable) {
        return ResponseEntity.ok().body(produtoService.acharTodosPaginado(descricao, pageable));
    }
}
