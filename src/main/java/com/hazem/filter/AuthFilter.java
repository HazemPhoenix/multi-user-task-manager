package com.hazem.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<String> excludedPaths = List.of("/jsp/login.jsp", "/jsp/register.jsp", "/login", "/register");
        String requestUri = req.getRequestURI();
        String path = requestUri.substring(req.getContextPath().length());

        boolean isExcluded = false;
        for(String p : excludedPaths) {
            if(p.equals(path)) {
                isExcluded = true;
                break;
            }
        }
        if(isExcluded || req.getSession().getAttribute("user") != null) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
        }
    }
}
