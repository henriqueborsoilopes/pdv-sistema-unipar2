package br.com.borsoitech.pdv.service;

import br.com.borsoitech.pdv.entity.Cliente;
import br.com.borsoitech.pdv.entity.Venda;
import br.com.borsoitech.pdv.repository.VendaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class VendaServico {
    
    private final VendaRepositorio vendaRepository;
    
    public VendaServico(VendaRepositorio vendaRepository) {
        this.vendaRepository = vendaRepository;
    }
    
    public Venda inserir(Venda venda) {
        if (venda.getCliente() == null) {
            venda.setCliente(new Cliente(null, "Consumidor Geral", null, null));
        }
        return vendaRepository.save(venda);
    }
}
