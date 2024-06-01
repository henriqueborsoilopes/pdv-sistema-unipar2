package br.com.borsoitech.pdv.layout.tableclient;

import br.com.borsoitech.pdv.model.type.Cliente;
import br.com.borsoitech.pdv.model.type.Pagina;

public interface IClienteCallback {
	
    void onPaginaLoaded(Pagina<Cliente> pagina);
    void onFailure(String errorMessage);
}
