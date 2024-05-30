package br.com.borsoitech.pdv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.borsoitech.pdv.entity.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Cliente obj WHERE obj.nome LIKE %:nome%")
    Page<Cliente> findAllByNome(@Param("nome") String nome, Pageable pageable);
}
