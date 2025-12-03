package com.hazem.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseConnectionManager {
    private HikariDataSource dataSource;

    public DatabaseConnectionManager(String url, String user, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setDriverClassName("org.postgresql.Driver");

        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public void shutdown() {
        if(this.dataSource != null) {
            this.dataSource.close();
        }
    }

}
