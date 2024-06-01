package br.com.borsoitech.pdv.layout.report;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IRelatorioService {

    @GET("/relatorios/{id_venda}")
    Call<byte[]> gerarRelatorio(@Header("Authorization") String authorization, @Path("id_venda") Long id_venda);
}
