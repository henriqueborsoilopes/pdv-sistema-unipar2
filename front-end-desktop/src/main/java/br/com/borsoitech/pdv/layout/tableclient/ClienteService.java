package br.com.borsoitech.pdv.layout.tableclient;

import br.com.borsoitech.pdv.model.retrofit.RetrofitService;
import br.com.borsoitech.pdv.model.type.Cliente;
import br.com.borsoitech.pdv.model.type.Pagina;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClienteService {

    private IClienteService iClienteService;

    public ClienteService() {
        Retrofit retrofit = RetrofitService.getClient();
        iClienteService = retrofit.create(IClienteService.class);
    }

    public void getAllClientePaginado(String nome, String authHeader, final ClienteCallback callback) {
        Call<Pagina<Cliente>> request = iClienteService.getAllClientePaginado("Bearer " + authHeader);

        request.enqueue(new Callback<Pagina<Cliente>>() {
            @Override
            public void onResponse(Call<Pagina<Cliente>> call, Response<Pagina<Cliente>> response) {
                if (response.isSuccessful()) {
                    callback.onPaginaLoaded(response.body());
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<Pagina<Cliente>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
