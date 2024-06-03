package br.com.borsoitech.pdv.layout.pdv;

import br.com.borsoitech.pdv.model.retrofit.RetrofitService;
import br.com.borsoitech.pdv.model.type.Venda;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VendaService {

    private final IVendaService vendaService;
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    public VendaService() {
        Retrofit retrofit = RetrofitService.getClient();
        vendaService = retrofit.create(IVendaService.class);
    }

    public void salvarVenda(String authorization, Venda venda, final IVendaSalvaCallback callback) {
        Call<Void> request = vendaService.salvarVenda(AUTHORIZATION_HEADER_PREFIX + authorization, venda);

        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    String locationHeader = response.headers().get("Location");
                    Long vendaId = extrairIdDaUri(locationHeader);
                    callback.onVendaSalva(vendaId);
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void atualizarVenda(String authorization, Venda venda, final IVendaAtualizaCallback callback) {
        Call<Venda> request = vendaService.atualizarVenda(AUTHORIZATION_HEADER_PREFIX + authorization, venda);

        request.enqueue(new Callback<Venda>() {
            @Override
            public void onResponse(Call<Venda> call, Response<Venda> response) {
                if (response.isSuccessful()) {
                    callback.onVendaAtualizada(response.body());
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<Venda> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure(t.getMessage());
            }
        });
    }

    private Long extrairIdDaUri(String locationHeader) {
        if (locationHeader == null || locationHeader.isEmpty()) {
            return null;
        }

        try {
            String[] segments = locationHeader.split("/");
            String vendaIdString = segments[segments.length - 1];
            return Long.parseLong(vendaIdString);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}