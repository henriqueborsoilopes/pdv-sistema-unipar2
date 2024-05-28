package br.com.borsoitech.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.borsoitech.pdv.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

}
