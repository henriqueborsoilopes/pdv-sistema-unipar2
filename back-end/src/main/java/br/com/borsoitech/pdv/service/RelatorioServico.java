package br.com.borsoitech.pdv.service;

import br.com.borsoitech.pdv.service.dbconnection.DBConnection;
import br.com.borsoitech.pdv.service.exception.ReportException;
import br.com.borsoitech.pdv.service.util.RelatorioUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RelatorioServico {

    public byte[] gerarComprovante(Map<String, Object> parametros, String jrxmlPath) {
        try (Connection conn = DBConnection.getConnection()) {
            String adjustedPath = RelatorioUtil.adjustPath(jrxmlPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(adjustedPath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (JRException | SQLException e) {
            throw new ReportException("Erro ao gerar comprovante");
        }
    }
}
