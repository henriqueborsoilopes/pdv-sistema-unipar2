package br.com.borsoitech.pdv.controller.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public class VendaDTO implements Serializable {
	@Serial
    private static final long serialVersionUID = 1L;
	
    @Schema(description = "Desconto aplicado na venda", example = "10.0", minimum = "0.0")
    @NotNull(message = "Desconto não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "Desconto deve ser maior ou igual a 0")
    private Double desconto;

    @Schema(description = "Valor total da venda", example = "100.0", minimum = "0.0")
    @NotNull(message = "Valor total não pode ser nulo")
    @Positive(message = "Valor total deve ser positivo")
    private Double valorTotal;

    @Schema(description = "Valor total pago", example = "90.0", minimum = "0.0")
    @NotNull(message = "Valor total pago não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "Valor total pago deve ser maior ou igual a 0")
    private Double valorTotalPago;

    @Schema(description = "Valor parcial pago", example = "50.0", minimum = "0.0")
    @NotNull(message = "Valor parcial pago não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "Valor parcial pago deve ser maior ou igual a 0")
    private Double valorParcialPago;

    @Schema(description = "Indicador se a venda foi quitada", example = "true")
    @NotNull(message = "Venda quitada não pode ser nulo")
    private Boolean vendaQuitada;

    @Schema(description = "ID do cliente", example = "123")
    @NotNull(message = "ID do cliente não pode ser nulo")
    private Long clienteId;

    @Schema(description = "Lista de pagamentos", required = true)
    @NotNull(message = "Pagamentos não podem ser nulos")
    @Size(min = 1, message = "Deve haver pelo menos um pagamento")
    private List<PagamentoDTO> pagamentos = new ArrayList<>();

    @Schema(description = "Lista de itens da venda", required = true)
    @NotNull(message = "Itens não podem ser nulos")
    @Size(min = 1, message = "Deve haver pelo menos um item")
    private List<ItemVendaDTO> itens = new ArrayList<>();

    public VendaDTO() { }

    public VendaDTO(Double desconto, Double valorTotal, Double valorTotalPago, Double valorParcialPago, Boolean vendaQuitada, Long clienteId) {
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
