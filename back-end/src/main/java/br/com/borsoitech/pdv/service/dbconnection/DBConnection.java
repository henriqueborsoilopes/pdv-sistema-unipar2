package br.com.borsoitech.pdv.service.dbconnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {

    private static String datasourceURL;
    private static String datasourceUsername;
    private static String datasourcePassword;

    @Value("${spring.datasource.url}")
    public void setDatasourceURL(String datasourceURL) {
        DBConnection.datasourceURL = datasourceURL;
    }

    @Value("${spring.datasource.username}")
    public void setDatasourceUsername(String datasourceUsername) {
        DBConnection.datasourceUsername = datasourceUsername;
    }

    @Value("${spring.datasource.password}")
    public void setDatasourcePassword(String datasourcePassword) {
        DBConnection.datasourcePassword = datasourcePassword;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(datasourceURL, datasourceUsername, datasourcePassword);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
