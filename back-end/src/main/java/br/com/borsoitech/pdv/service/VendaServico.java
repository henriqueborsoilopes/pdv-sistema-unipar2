package br.com.borsoitech.pdv.service;

import br.com.borsoitech.pdv.entity.Cliente;
import br.com.borsoitech.pdv.entity.Pagamento;
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
        Cliente cliente = clienteServico.getById(venda.getCliente().getId());
        for (ItemVenda item : venda.getItens()) {
            item.setVenda(venda);
            item.setProduto(produtoServico.getById(item.getProduto().getId()));
        }
        for (Pagamento pagamento : venda.getPagamentos()) {
            pagamento.setVenda(venda);
        }
        venda.setId(null);
        venda.setCliente(cliente);
        return vendaRepository.save(venda);
    }

    public Venda updateCarrinho(Venda venda) {
    	if (!venda.getItens().isEmpty()) {
            for (ItemVenda item : venda.getItens()) {
                produtoServico.getById(item.getProduto().getId());
            }
        }
        if (venda.getCliente() != null) {
            clienteServico.getById(venda.getCliente().getId());
        }
        return venda;
    }
}
