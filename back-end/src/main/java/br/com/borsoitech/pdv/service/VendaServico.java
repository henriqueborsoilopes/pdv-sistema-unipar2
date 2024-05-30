package br.com.borsoitech.pdv.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.borsoitech.pdv.entity.ItemVenda;
import br.com.borsoitech.pdv.entity.Venda;
import br.com.borsoitech.pdv.repository.VendaRepositorio;

@Service
public class VendaServico {
    
    private final VendaRepositorio vendaRepository;
    private final ProdutoServico produtoServico;
    private final ClienteServico clienteServico;
    
    public VendaServico(VendaRepositorio vendaRepository, ProdutoServico produtoServico, ClienteServico clienteServico) {
        this.vendaRepository = vendaRepository;
        this.produtoServico = produtoServico;
        this.clienteServico = clienteServico;
    }

    @Transactional
    public Venda inserir(Venda venda) {
        return vendaRepository.save(venda);
    }

    public Venda updateCarrinho(Venda venda) {
    	for (ItemVenda item : venda.getItens()) {
    		produtoServico.getById(item.getProduto().getId());
    	}
        return venda;
    }
}
