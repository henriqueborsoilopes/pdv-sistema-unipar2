package br.com.borsoitech.pdv.layout.pdv;

import br.com.borsoitech.pdv.model.type.Venda;
import retrofit2.Call;
import retrofit2.http.*;

public interface IVendaService {

    @POST("/vendas")
    Call<Void> salvarVenda(@Header("Authorization") String authorization, @Body Venda venda);

    @PUT("/vendas")
    Call<Venda> atualizarVenda(@Header("Authorization") String authorization, @Body Venda venda);
}