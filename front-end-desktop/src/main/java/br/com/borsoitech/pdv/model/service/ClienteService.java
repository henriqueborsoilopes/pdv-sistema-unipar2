package br.com.borsoitech.pdv.model.service;

import br.com.borsoitech.pdv.model.service.interfaces.IClienteService;
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

    public void getAllClientePaginado(String nome, String authHeader) {
        Call<Pagina<Cliente>> pagina = iClienteService.getAllClientePaginado(nome, authHeader);

        pagina.enqueue(new Callback<Pagina<Cliente>>() {
            @Override
            public void onResponse(Call<Pagina<Cliente>> call, Response<Pagina<Cliente>> response) {
                if (response.isSuccessful()) {
                    System.out.println("Total de páginas: " + response.body().getTotalPages());
                } else {
                    System.out.println("Login failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Pagina<Cliente>> call, Throwable t) {
                System.out.println("Login request failed: " + t.getMessage());
            }
        });
    }
}
