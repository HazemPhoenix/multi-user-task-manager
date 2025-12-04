package com.hazem.controller;

import com.hazem.config.DatabaseConnectionManager;
import com.hazem.dao.TaskDAO;
import com.hazem.dao.TaskDAOImpl;
import com.hazem.model.Task;
import com.hazem.model.User;
import com.hazem.service.TaskService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/tasks/*")
public class TaskServlet extends HttpServlet {
    private TaskService taskService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        DatabaseConnectionManager dbManager = (DatabaseConnectionManager) servletConfig.getServletContext().getAttribute("DB_MANAGER");
        TaskDAO taskDAO = new TaskDAOImpl();
        this.taskService = new TaskService(dbManager, taskDAO);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo.equals("/newTask")) {
            handleAddTask(req, resp);
        } 
        else if (req.getParameter("_method") != null) {
            if(req.getParameter("_method").equalsIgnoreCase("DELETE")) handleDeleteTask(req, resp);
            if(req.getParameter("_method").equalsIgnoreCase("PUT")) handleUpdateTask(req, resp);
        }
    }

    private void handleUpdateTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        boolean isDone = req.getParameter("isDone") != null;
        int taskId =  Integer.parseInt(req.getParameter("id"));
        this.taskService.updateTask(taskId, name, isDone);
        resp.sendRedirect(req.getContextPath() + "/tasks");
    }

    private void handleDeleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.length() <= 1) {
            resp.sendRedirect("tasks");
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));
        this.taskService.deleteTaskById(id);

        resp.sendRedirect(req.getContextPath() + "/tasks");
    }

    private void handleAddTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        boolean isDone = req.getParameter("isDone") != null;
        int userId =  ((User) req.getSession().getAttribute("user")).getId();
        this.taskService.addTask(userId, name, isDone);
        resp.sendRedirect(req.getContextPath() + "/tasks");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User) req.getSession().getAttribute("user")).getId();
        List<Task> userTasks = this.taskService.getUserTasks(userId);
        User user = (User) req.getSession().getAttribute("user");
        user.setTasks(userTasks);
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("tasks", userTasks);
        RequestDispatcher rd = req.getRequestDispatcher("jsp/tasks.jsp");
        rd.forward(req, resp);
    }

}
