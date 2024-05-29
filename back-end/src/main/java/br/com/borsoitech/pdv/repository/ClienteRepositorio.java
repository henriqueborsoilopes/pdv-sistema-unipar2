package br.com.borsoitech.pdv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.borsoitech.pdv.entity.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAllByNome(String nome, Pageable pageable);
}
