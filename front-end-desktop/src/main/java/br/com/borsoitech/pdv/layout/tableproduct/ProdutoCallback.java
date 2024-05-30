package br.com.borsoitech.pdv.layout.tableproduct;

import br.com.borsoitech.pdv.model.type.Pagina;
import br.com.borsoitech.pdv.model.type.Produto;

public interface ProdutoCallback {
	
    void onPaginaLoaded(Pagina<Produto> pagina);
    void onFailure(String errorMessage);
}
