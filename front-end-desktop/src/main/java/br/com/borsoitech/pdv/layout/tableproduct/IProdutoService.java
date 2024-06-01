package br.com.borsoitech.pdv.layout.tableproduct;

import br.com.borsoitech.pdv.model.type.Pagina;
import br.com.borsoitech.pdv.model.type.Produto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IProdutoService {

    @GET("/produtos")
    Call<Pagina<Produto>> getAllProdutoPaginado(@Header("Authorization") String authorization, @Query("page") int numPage);
}