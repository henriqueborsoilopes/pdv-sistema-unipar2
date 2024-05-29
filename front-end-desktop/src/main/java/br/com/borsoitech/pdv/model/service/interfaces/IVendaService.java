package br.com.borsoitech.pdv.model.service.interfaces;

import br.com.borsoitech.pdv.model.type.Venda;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface IVendaService {

    @GET("/vendas")
    Call<List<Venda>> getAllVendas();
}