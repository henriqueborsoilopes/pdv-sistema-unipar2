package br.com.borsoitech.pdv.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.borsoitech.pdv.entity.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
    
//    public PaginaDTO<Produto> acharTodosPaginado(String nome, int numPagina, int tamPagina) {        
//        EntityManager em = ConexaoBD.getEntityManager();
//        
//        String query = "SELECT DISTINCT obj FROM Produto obj WHERE obj.descricao LIKE :descricao ORDER BY obj.descricao ASC";
//        TypedQuery<Produto> authorQuery = em.createQuery(query, Produto.class);
//        authorQuery.setParameter("descricao", "%" + nome + "%");
//        authorQuery.setFirstResult(numPagina);
//        authorQuery.setMaxResults(tamPagina);
//        
//        TypedQuery<Long> countQuery = em.createQuery("SELECT COUNT(obj) FROM Produto obj WHERE obj.descricao LIKE :nome", Long.class);
//        countQuery.setParameter("nome", "%" + nome + "%");
//        long totalElementos = countQuery.getSingleResult();
//        
//        List<Produto> produtos = authorQuery.getResultList();
//        
//        ConexaoBD.closeEntityManager();
//        
//        PaginaDTO<Produto> entidades = new PaginaDTO(numPagina, tamPagina, totalElementos);
//        entidades.getConteudo().addAll(produtos);
//        
//        return entidades;
//    }
}
