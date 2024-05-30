package br.com.borsoitech.pdv.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.borsoitech.pdv.entity.Venda;
import br.com.borsoitech.pdv.service.VendaServico;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Venda", description = "API para gerenciamento de venda")
@RestController
@RequestMapping("/vendas")
public class VendaController {
	
	//@Parameter(description = "ID do item")
	//@Schema(description = "Nome do item")

    private final VendaServico vendaServico;

    public VendaController(VendaServico vendaServico) {
        this.vendaServico = vendaServico;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Venda a ser criado", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venda.class))) @RequestBody Venda venda) {
        vendaServico.inserir(venda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Venda> updateCarrinho(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Venda a ser atualizada", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venda.class))) @RequestBody Venda venda) {
        updateCarrinho(venda);
        return ResponseEntity.ok().body(venda);
    }
}
