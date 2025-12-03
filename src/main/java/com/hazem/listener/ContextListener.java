package com.hazem.listener;

import com.hazem.config.DatabaseConnectionManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(dbUrl, dbUser, dbPassword);
        ServletContext sc = sce.getServletContext();

        sc.setAttribute("DB_MANAGER", connectionManager);
        System.out.println("Connection manager registered successfully!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DatabaseConnectionManager dbManager = (DatabaseConnectionManager) sce.getServletContext().getAttribute("DB_MANAGER");
        dbManager.shutdown();
    }
}
