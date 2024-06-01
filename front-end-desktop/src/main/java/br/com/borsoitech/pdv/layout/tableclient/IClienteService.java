package br.com.borsoitech.pdv.layout.tableclient;

import br.com.borsoitech.pdv.model.type.Cliente;
import br.com.borsoitech.pdv.model.type.Pagina;
import retrofit2.Call;
import retrofit2.http.*;

public interface IClienteService {

    @GET("/clientes")
    Call<Pagina<Cliente>> getAllClientePaginado(@Header("Authorization") String authorization, @Query("page") int numPage);
}