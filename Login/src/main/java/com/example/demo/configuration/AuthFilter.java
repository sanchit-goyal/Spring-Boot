package com.example.demo.configuration;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

// @Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Principal userPrincipal = request.getUserPrincipal();
        final String loginPath = request.getContextPath().concat("/login");
        if (userPrincipal == null && !loginPath.equalsIgnoreCase(request.getRequestURI())) {
            ((HttpServletResponse) servletResponse).sendRedirect(loginPath);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
