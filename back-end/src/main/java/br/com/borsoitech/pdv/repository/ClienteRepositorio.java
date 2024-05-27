package br.com.borsoitech.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.borsoitech.pdv.entity.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    
//    public PaginaDTO acharTodosPaginado(String nome, int numPagina, int tamPagina) {
//        EntityManager em = ConexaoBD.getEntityManager();
//        
//        String query = "SELECT DISTINCT obj FROM Cliente obj WHERE obj.nome LIKE :nome ORDER BY obj.nome ASC";
//        TypedQuery<Cliente> authorQuery = em.createQuery(query, Cliente.class);
//        authorQuery.setParameter("nome", "%" + nome + "%");
//        authorQuery.setFirstResult(numPagina * tamPagina);
//        authorQuery.setMaxResults(tamPagina);
//        
//        TypedQuery<Long> countQuery = em.createQuery("SELECT COUNT(obj) FROM Cliente obj WHERE obj.nome LIKE :nome", Long.class);
//        countQuery.setParameter("nome", "%" + nome + "%");
//        long totalElementos = countQuery.getSingleResult();
//        
//        List<Cliente> clientes = authorQuery.getResultList();
//        
//        ConexaoBD.closeEntityManager();
//        
//        PaginaDTO<Cliente> entidades = new PaginaDTO(numPagina, tamPagina, totalElementos);
//        entidades.getConteudo().addAll(clientes);
//        
//        return entidades;
//    }
}
