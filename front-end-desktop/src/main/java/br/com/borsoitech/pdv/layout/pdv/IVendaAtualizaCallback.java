package br.com.borsoitech.pdv.layout.pdv;

import br.com.borsoitech.pdv.model.type.Venda;

public interface IVendaAtualizaCallback {

    void onVendaAtualizada(Venda venda);
    void onFailure(String errorMessage);
}
