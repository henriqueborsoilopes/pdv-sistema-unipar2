package br.com.borsoitech.pdv.model.type.enums;

public enum TipoPagamento {
    
    DINHEIRO(1, "Dinheiro", 1),
    CARTAO_CREDITO(2, "Cartão de Crédito", 3),
    CARTAO_DEBITO(3, "Cartão de Débito", 1),
    PIX(4, "Pix", 1);
    
    private Integer codigo;
    private String descricao;
    private Integer qtdParcelas;
    
    private TipoPagamento(Integer codigo, String descricao, Integer qtdParcelas) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.qtdParcelas = qtdParcelas;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(Integer qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }
    
    public static TipoPagamento paraEnum(String descricao){ 
        if (descricao == null) {
            return null;
        }
        for (TipoPagamento tipo : TipoPagamento.values()) {
            if (tipo.getDescricao().equals(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Descrição inválido! " + descricao);
    }
    
    public static TipoPagamento paraEnum(Integer codigo){ 
        if (codigo == null) {
            return null;
        }
        for (TipoPagamento tipo : TipoPagamento.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Descrição inválido! " + codigo);
    }
}
