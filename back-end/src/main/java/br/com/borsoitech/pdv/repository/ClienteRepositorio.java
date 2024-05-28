package br.com.borsoitech.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.borsoitech.pdv.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
