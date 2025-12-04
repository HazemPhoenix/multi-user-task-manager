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
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.INSTANCE;
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
