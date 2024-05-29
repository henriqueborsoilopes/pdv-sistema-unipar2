package br.com.borsoitech.pdv.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.borsoitech.pdv.entity.Cliente;
import br.com.borsoitech.pdv.service.ClienteServico;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServico clienteServico;
    
    public ClienteController(ClienteServico clienteServico) {
        this.clienteServico = clienteServico;
    }

//    @ApiOperation(value = "Endpoint Lista todas Pessoas")
    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllPaged(@RequestParam(name = "nome", defaultValue = "") String nome, Pageable pageable) {
        return ResponseEntity.ok().body(clienteServico.acharTodosPaginado(nome, pageable));
    }
}
