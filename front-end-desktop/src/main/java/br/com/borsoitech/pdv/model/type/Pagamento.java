package br.com.borsoitech.pdv.model.type;

import java.io.Serial;
import java.io.Serializable;

public class Pagamento  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer parcelas;
    private Double valorPago;
    private Integer tipoPag;

    public Pagamento() { }

    public Pagamento(Integer parcelas, Double valorPago, Integer tipoPag) {
        super();
        this.parcelas = parcelas;
        this.valorPago = valorPago;
        this.tipoPag = tipoPag;
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

    public Integer getTipoPag() {
        return tipoPag;
    }

    public void setTipoPag(Integer tipoPag) {
        this.tipoPag = tipoPag;
    }
}
