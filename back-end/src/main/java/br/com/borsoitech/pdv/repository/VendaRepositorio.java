package br.com.borsoitech.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.borsoitech.pdv.entity.Venda;

public interface VendaRepositorio extends JpaRepository<Venda, Long>{

//    public Venda inserir(Venda venda) {
//        EntityManager em = ConexaoBD.getEntityManager();
//        venda = em.merge(venda);
//        return venda;
//    }
}
