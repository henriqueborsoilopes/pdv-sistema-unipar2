package br.com.borsoitech.pdv.controller;

import org.springdoc.api.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.borsoitech.pdv.entity.Produto;
import br.com.borsoitech.pdv.service.ProdutoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos", description = "contém todos os recursos relacionados com produto, como: página de produtos.")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private ProdutoServico produtoService;
    
    public ProdutoController(ProdutoServico produtoService) {
        this.produtoService = produtoService;
    }
    
    @Operation(summary = "Página de produtos",
            description = "Recurso para listar produtos páginado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(mediaType = "Application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping
    public ResponseEntity<Page<Produto>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok().body(produtoService.acharTodosPaginado(pageable));
    }
}
