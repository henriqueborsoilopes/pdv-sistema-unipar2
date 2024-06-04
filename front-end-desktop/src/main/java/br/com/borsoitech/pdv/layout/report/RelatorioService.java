package br.com.borsoitech.pdv.layout.report;

import br.com.borsoitech.pdv.layout.pdv.IVendaService;
import br.com.borsoitech.pdv.model.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.awt.*;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RelatorioService {

    private final IRelatorioService relatorioService;
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    public RelatorioService() {
        Retrofit retrofit = RetrofitService.getClient();
        relatorioService = retrofit.create(IRelatorioService.class);
    }

    public void gerarRelatorio(String authorization, Long id_venda, final IRelatorioCallback callback) {
        Call<byte[]> call = relatorioService.gerarRelatorio(AUTHORIZATION_HEADER_PREFIX + authorization, id_venda);
        call.enqueue(new Callback<byte[]>() {
            @Override
            public void onResponse(Call<byte[]> call, Response<byte[]> response) {
                if (response.isSuccessful()) {
                    try {
                        Path pdfPath = Paths.get("comprovante.pdf");
                        try (FileOutputStream fos = new FileOutputStream(pdfPath.toFile())) {
                            fos.write(response.body());
                        }
                        if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().open(pdfPath.toFile());
                        }
                        callback.onRelatorioGerado();
                    } catch (Exception ex) {
                        callback.onFailure(ex.getMessage());
                    }
                } else {
                    callback.onFailure("Erro ao processar o comprovante.");
                }
            }

            @Override
            public void onFailure(Call<byte[]> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
