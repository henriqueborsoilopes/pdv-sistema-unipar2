package br.com.borsoitech.pdv.layout.report;

import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.borsoitech.pdv.model.retrofit.RetrofitService;
import okhttp3.ResponseBody;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RelatorioService {

    private final IRelatorioService relatorioService;
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    public RelatorioService() {
        Retrofit retrofit = RetrofitService.getClient();
        relatorioService = retrofit.create(IRelatorioService.class);
    }

    public void gerarRelatorio(String authorization, Long id_venda, final IRelatorioCallback callback) {
        Call<ResponseBody> call = relatorioService.gerarRelatorio(AUTHORIZATION_HEADER_PREFIX + authorization, id_venda);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        byte[] pdfBytes = response.body().bytes();
                        processarPdf(pdfBytes, callback);
                    } catch (Exception ex) {
                        callback.onFailure(ex.getMessage());
                    }
                } else {
                    callback.onFailure("Erro ao processar o comprovante.");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    private void processarPdf(byte[] pdfBytes, final IRelatorioCallback callback) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(pdfBytes);
             PDDocument document = PDDocument.load(bais)) {
            if (verificarImpressoraDisponivel()) {
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                printerJob.setPageable(new PDFPageable(document));
                if (printerJob.printDialog()) {
                    printerJob.print();
                    callback.onRelatorioGerado();
                }
            } else {
                gerarEVisualizarPDF(pdfBytes, callback);
            }
        } catch (IOException | PrinterException ex) {
            callback.onFailure("Erro ao processar o comprovante: " + ex.getMessage());
        }
    }


    private boolean verificarImpressoraDisponivel() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        return printerJob.printDialog();
    }

    private void gerarEVisualizarPDF(byte[] pdfBytes, final IRelatorioCallback callback) {
        try {
            Path pdfPath = Paths.get("comprovante.pdf");
            try (FileOutputStream fos = new FileOutputStream(pdfPath.toFile())) {
                fos.write(pdfBytes);
            }
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfPath.toFile());
            }
            callback.onRelatorioGerado();
        } catch (Exception ex) {
            callback.onFailure(ex.getMessage());
        }
    }

}
