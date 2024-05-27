package br.com.borsoitech.pdv.service;

import br.com.borsoitech.pdv.repository.ProdutoRepositorio;

public class ProdutoServico {
    
    private final ProdutoRepositorio repository;
    
    public ProdutoServico(ProdutoRepositorio repository) {
        this.repository = repository;
    }
    
//    public PaginaDTO<Produto> acharTodosPaginado(String nome, int numPagina, int tamPagina) {
//        return repository.acharTodosPaginado(nome, numPagina, tamPagina);
//    }
}
