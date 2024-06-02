package br.com.borsoitech.pdv.controller.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ItemVendaUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "Valor total do item", example = "200.0", minimum = "0.0")
	@NotNull(message = "Valor total do item não pode ser nulo")
	@DecimalMin(value = "0.0", inclusive = true, message = "Valor total do item deve ser maior ou igual a 0")
	private Double valorTotalItem;

	@Schema(description = "Descrição do item", example = "Descrição do produto")
	@NotNull(message = "Descrição não pode ser nula")
	@Size(min = 1, message = "Descrição não pode estar vazia")
	private String descricao;

	@Schema(description = "Quantidade do item", example = "5", minimum = "1")
	@NotNull(message = "Quantidade não pode ser nula")
	@Positive(message = "Quantidade deve ser um número positivo")
	private Integer quantidade;

	@Schema(description = "Valor unitário do item", example = "40.0", minimum = "0.0")
	@NotNull(message = "Valor unitário não pode ser nulo")
	@DecimalMin(value = "0.0", inclusive = true, message = "Valor unitário deve ser maior ou igual a 0")
	private Double valorUnit;

	@Schema(description = "Desconto no item", example = "10.0", minimum = "0.0")
	@NotNull(message = "Desconto não pode ser nulo")
	@DecimalMin(value = "0.0", inclusive = true, message = "Desconto deve ser maior ou igual a 0")
	private Double desconto;

	@Schema(description = "ID do produto", example = "123")
	@NotNull(message = "ID do produto não pode ser nulo")
	private Long produtoId;
    
    public ItemVendaUpdateDTO() { }

	public ItemVendaUpdateDTO(String descricao, Integer quantidade, Double valorUnit, Double desconto, Long produtoId) {
		super();
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valorUnit = valorUnit;
		this.desconto = desconto;
		this.produtoId = produtoId;
	}
	
    public Double getValorTotalItem() {
		return valorTotalItem;
	}

	public void setValorTotalItem(Double valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}

	public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    
    public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
}
