package com.hazem.controller;

import com.hazem.config.DatabaseConnectionManager;
import com.hazem.dao.TaskDAO;
import com.hazem.dao.TaskDAOImpl;
import com.hazem.model.User;
import com.hazem.service.TaskService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/tasks/*")
public class TaskServlet extends HttpServlet {
    private TaskService taskService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        DatabaseConnectionManager dbManager = (DatabaseConnectionManager) servletConfig.getServletContext().getAttribute("DB_MANAGER");
        TaskDAO taskDAO = new TaskDAOImpl();
//        this.taskService = new TaskService(dbManager, taskDAO);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        boolean isDone = req.getParameter("isDone") != null;
        int userId =  ((User) req.getSession().getAttribute("user")).getId();
        this.taskService.addTask(userId, name, isDone);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
