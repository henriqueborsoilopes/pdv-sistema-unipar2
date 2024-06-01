package br.com.borsoitech.pdv.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RelatorioServico {

    public byte[] gerarComprovante(Long id_venda, Map<String, Object> parametros, String jrxmlPath) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {

                //InputStream inputStream = getClass().getResourceAsStream(jrxmlPath);
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\henrique.lopes\\Projeto\\pdv-sistema-unipar2\\back-end\\src\\main\\java\\br\\com\\borsoitech\\pdv\\service\\relatorio\\comprovante_venda.jrxml");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (JRException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
