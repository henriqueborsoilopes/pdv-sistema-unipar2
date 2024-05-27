package br.com.borsoitech.pdv.model.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venda {
    
    private Long id;
    private Double desconto;
    
    private Cliente cliente;
    
    private List<Pagamento> pagamentos = new ArrayList<>();

    private List<ItemVenda> itens = new ArrayList<>();
    
    public Venda() { }

    public Venda(Long id, Double desconto, Cliente cliente) {
        this.id = id;
        this.desconto = desconto;
        this.cliente = cliente;
    }
    
    public boolean vendaQuitada() {
        return getValorTotalPago().equals(getValorTotal());
    }
    
    public Double getValorParcialPago() {
        return getValorTotal() - getValorTotalPago() - desconto;
    }
    
    public Double getValorTotalPago() {
        double pago = 0.0;
        for (Pagamento pagamento : pagamentos) {
            pago += pagamento.getValorPago();
        }
        return pago;
    }
    
    public Double getValorTotal() {
        double valorTotalItem = 0.0;
        for (ItemVenda item : itens) {
            valorTotalItem += item.getValorTotalItem();
        }
        return valorTotalItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void addPagamento(Pagamento pagamento) {
        this.pagamentos.add(pagamento);
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void addItem(ItemVenda item) {
        this.itens.add(item);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        return Objects.equals(this.id, other.id);
    }
}
