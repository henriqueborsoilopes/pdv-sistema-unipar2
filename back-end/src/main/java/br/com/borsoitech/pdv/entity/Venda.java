package br.com.borsoitech.pdv.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_venda")
public class Venda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double desconto;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST)
    private List<Pagamento> pagamentos = new ArrayList<>();
    
    @OneToMany(mappedBy = "id.venda", cascade = CascadeType.PERSIST)
    private List<ItemVenda> itens = new ArrayList<>();
    
    public Venda() { }

    public Venda(Long id, Double desconto, Cliente cliente) {
        this.id = id;
        this.desconto = desconto;
        this.cliente = cliente;
    }
    
    public boolean vendaQuitada() {
        return getValorTotalPago().equals(getValorTotal()) && !getValorTotal().equals(0.0);
    }
    
    public Double getValorParcialPago() {
        return getValorTotal() - getValorTotalPago() - desconto;
    }
    
    public Double getValorTotalPago() {
        double pago = 0.0;
        if (!pagamentos.isEmpty()) {
            for (Pagamento pagamento : pagamentos) {
                pago += pagamento.getValorPago();
            }
        }
        return pago;
    }
    
    public Double getValorTotal() {
        double valorTotalItem = 0.0;
        if (!itens.isEmpty()) {
            for (ItemVenda item : itens) {
                valorTotalItem += item.getValorTotalItem();
            }
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
