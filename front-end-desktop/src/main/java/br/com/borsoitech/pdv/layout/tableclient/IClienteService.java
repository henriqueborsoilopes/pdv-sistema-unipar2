package br.com.borsoitech.pdv.layout.tableclient;

import br.com.borsoitech.pdv.model.type.Cliente;
import br.com.borsoitech.pdv.model.type.Pagina;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface IClienteService {
    
    @GET("/clientes")
    Call<Pagina<Cliente>> getAllClientePaginado(@Header("Authorization") String authorization);
}