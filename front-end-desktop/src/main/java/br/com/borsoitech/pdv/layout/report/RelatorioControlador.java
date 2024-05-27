package br.com.borsoitech.pdv.layout.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioControlador {

    private static final String COMPROVANTE_VENDA_PATH = "C:\\Projetos\\pdv-sistema-unipar\\pdvsistema\\src\\main\\java\\br\\unipar\\pdvsistema\\tela\\relatorio\\comprovante_venda.jasper";
    
    public static void carregaRelatori(Long id_venda) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdvsistema", "postgres", "12345678")) {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(COMPROVANTE_VENDA_PATH);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ID_VENDA", id_venda);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);
            
//            String outputFile = "C:\\Projetos\\pdv-sistema-unipar\\pdvsistema\\src\\main\\java\\br\\unipar\\pdvsistema\\tela\\relatorio\\";
//            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile);
//            System.out.println("PDF gerado em: " + outputFile);
        } catch (JRException | SQLException e) {
        }
    }
}
