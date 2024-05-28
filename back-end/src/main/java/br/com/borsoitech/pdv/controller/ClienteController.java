package br.com.borsoitech.pdv.controller;

import br.com.borsoitech.pdv.entity.Cliente;
import br.com.borsoitech.pdv.service.ClienteServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServico clienteServico;
    
    public ClienteController(ClienteServico clienteServico) {
        this.clienteServico = clienteServico;
    }
    
    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok().body(clienteServico.acharTodosPaginado(pageable));
    }
}
