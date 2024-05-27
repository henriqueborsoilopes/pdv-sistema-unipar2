package br.com.borsoitech.pdv.service;

import br.com.borsoitech.pdv.repository.ClienteRepositorio;

public class ClienteServico {
   
    private final ClienteRepositorio repository;
    
    public ClienteServico(ClienteRepositorio repository) {
        this.repository = repository;
    }
    
//    public PaginaDTO<Cliente> acharTodosPaginado(String nome, int numPagina, int tamPagina) {
//        return repository.acharTodosPaginado(nome, numPagina, tamPagina);
//    }
}
