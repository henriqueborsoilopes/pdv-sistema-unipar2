package br.com.borsoitech.pdv.layout.tableclient;

import br.com.borsoitech.pdv.layout.util.Log;
import br.com.borsoitech.pdv.model.retrofit.RetrofitService;
import br.com.borsoitech.pdv.model.type.Cliente;
import br.com.borsoitech.pdv.model.type.Pagina;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClienteService {

    private final IClienteService iClienteService;
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
    private final Log log;

    public ClienteService() {
        Retrofit retrofit = RetrofitService.getClient();
        iClienteService = retrofit.create(IClienteService.class);
        log = new Log();
    }

    public void getAllClientesPaginados(String nome, String authHeader, int numPg, final IClienteCallback callback) {
        Call<Pagina<Cliente>> request = iClienteService.getAllClientePaginado(AUTHORIZATION_HEADER_PREFIX + authHeader, numPg);

        request.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Pagina<Cliente>> call, Response<Pagina<Cliente>> response) {
                if (response.isSuccessful()) {
                    callback.onPaginaLoaded(response.body());
                    log.logInfo("Operação de listagem de clientes paginado", String.valueOf(response.code()));
                } else {
                    callback.onFailure(response.message());
                    log.logError("Operação de listagem de clientes paginado", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Pagina<Cliente>> call, Throwable t) {
                callback.onFailure(t.getMessage());
                log.logInfo("Operação de listagem de clientes paginado", "500");
            }
        });
    }
}
