package br.com.borsoitech.pdv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.borsoitech.pdv.entity.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj WHERE obj.descricao LIKE %:descricao%")
    Page<Produto> findAllByDescricao(@Param("descricao") String descricao, Pageable pageable);
}
