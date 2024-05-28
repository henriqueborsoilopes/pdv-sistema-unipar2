package br.com.borsoitech.pdv.service;

import br.com.borsoitech.pdv.entity.Cliente;
import br.com.borsoitech.pdv.repository.ClienteRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServico {
   
    private final ClienteRepositorio repository;
    
    public ClienteServico(ClienteRepositorio repository) {
        this.repository = repository;
    }
    
    public Page<Cliente> acharTodosPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
