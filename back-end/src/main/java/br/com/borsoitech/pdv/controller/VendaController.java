package br.com.borsoitech.pdv.controller;

import br.com.borsoitech.pdv.entity.Venda;
import br.com.borsoitech.pdv.service.VendaServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaServico vendaServico;

    public VendaController(VendaServico vendaServico) {
        this.vendaServico = vendaServico;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Venda venda) {
        vendaServico.inserir(venda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Venda> updateCarrinho(@RequestBody Venda venda) {
        updateCarrinho(venda);
        return ResponseEntity.ok().body(venda);
    }
}
