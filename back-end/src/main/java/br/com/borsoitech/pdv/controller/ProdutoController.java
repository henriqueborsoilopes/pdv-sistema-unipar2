package br.com.borsoitech.pdv.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.borsoitech.pdv.entity.Produto;
import br.com.borsoitech.pdv.service.ProdutoServico;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private ProdutoServico produtoService;
    
    public ProdutoController(ProdutoServico produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> getAllPaged(@RequestParam(name = "descricao", defaultValue = "") String descricao, Pageable pageable) {
        return ResponseEntity.ok().body(produtoService.acharTodosPaginado(descricao, pageable));
    }
}
