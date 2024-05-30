package br.com.borsoitech.pdv.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;

public class PagamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "Número de parcelas", example = "12", minimum = "1")
	@NotNull(message = "O número de parcelas não pode ser nulo")
	@Positive(message = "O número de parcelas deve ser positivo")
	private Integer parcelas;

	@Schema(description = "Valor pago", example = "100.0", minimum = "0.0")
	@NotNull(message = "Valor pago não pode ser nulo")
	@DecimalMin(value = "0.0", inclusive = true, message = "Valor pago deve ser maior ou igual a 0")
	private Double valorPago;

	@Schema(description = "Tipo de pagamento", example = "1", allowableValues = {"1", "2", "3", "4"})
	@NotNull(message = "O tipo de pagamento não pode ser nulo")
	@Positive(message = "O tipo de pagamento deve ser um número positivo")
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
