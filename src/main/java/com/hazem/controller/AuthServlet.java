package com.hazem.controller;

import com.hazem.config.DatabaseConnectionManager;
import com.hazem.dao.UserDAO;
import com.hazem.dao.UserDAOImpl;
import com.hazem.model.User;
import com.hazem.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = {"/register", "/login"})
public class AuthServlet extends HttpServlet {
    private UserService userService;


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        DatabaseConnectionManager dbManager = (DatabaseConnectionManager) servletConfig.getServletContext().getAttribute("DB_MANAGER");
        UserDAO userDAO = new UserDAOImpl();
        this.userService = new UserService(dbManager, userDAO);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String path = req.getServletPath();
        if(path.equals("/login")) handleLogin(req, resp);
        else handleRegister(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(this.userService.registerUser(username, email, password)) {
            resp.sendRedirect("../webapp/jsp/login.jsp");
        } else {
            PrintWriter out = resp.getWriter();
            out.println("Something went wrong, please try again.");
            req.getRequestDispatcher("../webapp/jsp/register.jsp").include(req, resp);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = this.userService.loginUser(email, password);

        if(user != null) {
            req.getSession().setAttribute("user", user);
        } else {
            PrintWriter out = resp.getWriter();
            out.println("Email and/or Password are incorrect.");
            req.getRequestDispatcher("../webapp/jsp/login.jsp").include(req, resp);
        }
    }
}
