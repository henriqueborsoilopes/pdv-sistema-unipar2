package br.com.borsoitech.pdv.model.type;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venda implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Double desconto = 0.0;
    private Double valorTotal = 0.0;
    private Double valorTotalPago = 0.0;
    private Double valorParcialPago = 0.0;
    private Boolean vendaQuitada = Boolean.FALSE;
    private Long clienteId;
    private boolean isNovaVenda = Boolean.TRUE;

    private final List<Pagamento> pagamentos = new ArrayList<>();
    private final List<ItemVenda> itens = new ArrayList<>();

    public Venda() { }

    public Venda(Double desconto, Double valorTotal, Double valorTotalPago, Double valorParcialPago,
                        Boolean vendaQuitada, Long clienteId) {
        super();
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.valorTotalPago = valorTotalPago;
        this.valorParcialPago = valorParcialPago;
        this.vendaQuitada = vendaQuitada;
        this.clienteId = clienteId;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorTotalPago() {
        return valorTotalPago;
    }

    public void setValorTotalPago(Double valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }

    public Double getValorParcialPago() {
        return valorParcialPago;
    }

    public void setValorParcialPago(Double valorParcialPago) {
        this.valorParcialPago = valorParcialPago;
    }

    public Boolean isVendaQuitada() {
        return vendaQuitada;
    }

    public void setVendaQuitada(Boolean vendaQuitada) {
        this.vendaQuitada = vendaQuitada;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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

    public boolean isNovaVenda() {
        return isNovaVenda;
    }

    public void setNovaVenda(boolean isNovaVenda) {
        this.isNovaVenda = isNovaVenda;
    }
}