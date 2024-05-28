package br.com.borsoitech.pdv.service;

import br.com.borsoitech.pdv.entity.Produto;
import br.com.borsoitech.pdv.repository.ProdutoRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServico {
    
    private final ProdutoRepositorio repository;
    
    public ProdutoServico(ProdutoRepositorio repository) {
        this.repository = repository;
    }
    
    public Page<Produto> acharTodosPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
