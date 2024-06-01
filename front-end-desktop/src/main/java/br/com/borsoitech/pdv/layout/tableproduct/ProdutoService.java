package br.com.borsoitech.pdv.layout.tableproduct;

import br.com.borsoitech.pdv.model.retrofit.RetrofitService;
import br.com.borsoitech.pdv.model.type.Pagina;
import br.com.borsoitech.pdv.model.type.Produto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProdutoService {
	
	private IProdutoService iProdutoService;
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

	public ProdutoService() {
        Retrofit retrofit = RetrofitService.getClient();
        iProdutoService = retrofit.create(IProdutoService.class);
    }

    public void getAllProdutoPaginado(String nome, String authHeader, int numPg, IProdutoCallback callback) {
        Call<Pagina<Produto>> request = iProdutoService.getAllProdutoPaginado(AUTHORIZATION_HEADER_PREFIX + authHeader, numPg);

        request.enqueue(new Callback<Pagina<Produto>>() {
            @Override
            public void onResponse(Call<Pagina<Produto>> call, Response<Pagina<Produto>> response) {
                if (response.isSuccessful()) {
                    callback.onPaginaLoaded(response.body());
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<Pagina<Produto>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
