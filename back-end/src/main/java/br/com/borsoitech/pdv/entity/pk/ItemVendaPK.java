package br.com.borsoitech.pdv.entity.pk;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.borsoitech.pdv.entity.Produto;
import br.com.borsoitech.pdv.entity.Venda;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ItemVendaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_venda")
    private Venda venda;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_produto")
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
