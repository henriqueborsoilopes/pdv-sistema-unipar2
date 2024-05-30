package br.com.borsoitech.pdv.model.service;

import java.util.List;

import br.com.borsoitech.pdv.model.type.Venda;

public class ProdutoService {

    public List<Venda> getAllVendas() {
//        IVendaService vendaService = RetrofitService.getRetrofitInstance().create(IVendaService.class);
//        Call<List<Venda>> call = vendaService.getAllVendas();
//        call.enqueue(new Callback<Venda>() {
//            @Override
//            public void onResponse(Call<Venda> call, Response<Venda> response) {
//                List<Venda> user = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<Venda> call, Throwable throwable) {
//                System.out.println(throwable);
//            }
//        });
//        try {
//            Response<List<Venda>> response = call.execute();
//
//            if (response.isSuccessful() && response.body() != null) {
//                return response.body();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }
}
