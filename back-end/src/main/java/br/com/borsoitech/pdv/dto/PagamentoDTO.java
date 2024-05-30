package br.com.borsoitech.pdv.dto;

import java.io.Serializable;

public class PagamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer parcelas;
    private Double valorPago;
    private Integer tipoPagamento;
    
    public PagamentoDTO() { }

    public PagamentoDTO(Integer parcelas, Double valorPago, Integer tipoPagamento) {
		super();
		this.parcelas = parcelas;
		this.valorPago = valorPago;
		this.tipoPagamento = tipoPagamento;
	}

	public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Integer getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(Integer tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
