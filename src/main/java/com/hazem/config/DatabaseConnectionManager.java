package com.hazem.config;

import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

public class DatabaseConnectionManager {
    public static PGSimpleDataSource getDataSource() {
       String url = System.getenv("DB_URL");
       String user = System.getenv("DB_USER");
       String password = System.getenv("DB_PASSWORD");

       PGSimpleDataSource dataSource = new PGSimpleDataSource();
       dataSource.setUrl(url);
       dataSource.setUser(user);
       dataSource.setPassword(password);

       return dataSource;
    }
}
