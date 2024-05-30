package br.com.borsoitech.pdv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VendaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Double desconto;
    private Double valorTotal;
    private Double valorTotalPago;
    private Double valorParcialPago;
    private Double vendaQuitada;
    private Long clienteId;
    
    private List<PagamentoDTO> pagamentos = new ArrayList<>();
    private List<ItemVendaDTO> itens = new ArrayList<>();
    
    public VendaDTO() { }

    public VendaDTO(Double desconto, Double valorTotal, Double valorTotalPago, Double valorParcialPago,
			Double vendaQuitada, Long clienteId) {
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

	public Double getVendaQuitada() {
		return vendaQuitada;
	}

	public void setVendaQuitada(Double vendaQuitada) {
		this.vendaQuitada = vendaQuitada;
	}

	public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    
    public List<PagamentoDTO> getPagamentos() {
        return pagamentos;
    }

    public void addPagamento(PagamentoDTO pagamento) {
        this.pagamentos.add(pagamento);
    }

    public List<ItemVendaDTO> getItens() {
        return itens;
    }

    public void addItem(ItemVendaDTO item) {
        this.itens.add(item);
    }
}
