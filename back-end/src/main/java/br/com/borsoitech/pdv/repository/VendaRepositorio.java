package br.com.borsoitech.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.borsoitech.pdv.entity.Venda;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepositorio extends JpaRepository<Venda, Long>{
    
}
