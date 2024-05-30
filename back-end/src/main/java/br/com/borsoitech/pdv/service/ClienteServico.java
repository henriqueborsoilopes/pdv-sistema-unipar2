package br.com.borsoitech.pdv.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.borsoitech.pdv.entity.Cliente;
import br.com.borsoitech.pdv.repository.ClienteRepositorio;
import br.com.borsoitech.pdv.service.exception.ControllerNotFoundException;

@Service
public class ClienteServico {
   
    private final ClienteRepositorio repository;
    
    public ClienteServico(ClienteRepositorio repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<Cliente> acharTodosPaginado(String nome, Pageable pageable) {
        return repository.findAllByNome(nome, pageable);
    }
    
    @Transactional(readOnly = true)
    public Cliente getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Cliente com ID " + id + " não foi encontrado."));
    }
}
