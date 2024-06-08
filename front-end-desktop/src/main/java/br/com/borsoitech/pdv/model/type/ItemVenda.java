package br.com.borsoitech.pdv.model.type;

import java.io.Serial;
import java.io.Serializable;

public class ItemVenda implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Double valorTotalItem;
    private String descricao;
    private Integer quantidade;
    private Double valorUnit;
    private Double desconto;
    private Long produtoId;

    public ItemVenda() { }

    public ItemVenda(Double valorTotalItem, String descricao, Integer quantidade, Double valorUnit, Double desconto, Long produtoId) {
        this.valorTotalItem = valorTotalItem;
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