package br.com.borsoitech.pdv.model.type.pk;

import java.util.Objects;

import br.com.borsoitech.pdv.model.type.Produto;
import br.com.borsoitech.pdv.model.type.Venda;

public class ItemVendaPK {
    
    private Venda venda;
    private Produto produto;
    
    public ItemVendaPK() { }

    public ItemVendaPK(Venda venda, Produto produto) {
        this.venda = venda;
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.venda);
        hash = 61 * hash + Objects.hashCode(this.produto);
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
        final ItemVendaPK other = (ItemVendaPK) obj;
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return Objects.equals(this.produto, other.produto);
    }
}
