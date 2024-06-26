package br.com.borsoitech.pdv.layout.report;

import java.awt.*;
import java.io.Serial;

import br.com.borsoitech.pdv.layout.popup.NotificationPopup;

import javax.swing.*;

public class RelatorioControlador extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    private JProgressBar progressBar;

    public RelatorioControlador(String authorization, Long id_venda) {
        setTitle("Gerar Relatório");
        setSize(400, 150);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        RelatorioService relatorioService = new RelatorioService();

        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(300, 10));
        progressBar.setForeground(Color.BLUE);
        progressBar.setStringPainted(false);
        progressBar.setValue(0);
        progressBar.setIndeterminate(true);

        relatorioService.gerarRelatorio(authorization, id_venda, new IRelatorioCallback() {
            @Override
            public void onRelatorioGerado() {
                progressBar.setIndeterminate(false);
                progressBar.setValue(100);
                showNotification("Comprovante gerado com sucesso!");
                dispose();
            }

            @Override
            public void onFailure(String errorMessage) {
                progressBar.setIndeterminate(false);
                progressBar.setValue(0);
                showNotification("Erro ao gerar o comprovante!");
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(progressBar);

        add(panel);
    }

    private void showNotification(String message) {
        NotificationPopup popup = new NotificationPopup(message);
        popup.showPopup();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
