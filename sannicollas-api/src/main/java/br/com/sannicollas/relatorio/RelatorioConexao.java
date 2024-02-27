package br.com.sannicollas.relatorio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class RelatorioConexao {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    @Value("${spring.datasource.url}")
    public void setUrlStatic(String url){
        RelatorioConexao.URL = url;
    }

    @Value("${spring.datasource.username}")
    public void setNameStatic(String username){
        RelatorioConexao.USERNAME = username;
    }

    @Value("${spring.datasource.password}")
    public void setPasswordStatic(String password){
        RelatorioConexao.PASSWORD = password;
    }

    @Bean
    public Connection connection (DataSource dataSource) throws SQLException {
        return dataSource.getConnection();
    }

    @Bean
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Properties props = new Properties();
            props.setProperty("user", USERNAME);
            props.setProperty("password", PASSWORD);
            props.setProperty("currentSchema", "public");

            conn = DriverManager.getConnection(URL, props);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
