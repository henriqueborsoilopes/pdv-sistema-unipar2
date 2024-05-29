package br.com.borsoitech.pdv.service;

import br.com.borsoitech.pdv.entity.Produto;
import br.com.borsoitech.pdv.repository.ProdutoRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoServico {
    
    private final ProdutoRepositorio repository;
    
    public ProdutoServico(ProdutoRepositorio repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<Produto> acharTodosPaginado(String descricao, Pageable pageable) {
        return repository.findAllByDescricao(descricao, pageable);
    }
}
