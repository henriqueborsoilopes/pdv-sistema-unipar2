package br.com.borsoitech.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.borsoitech.pdv.entity.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

}
