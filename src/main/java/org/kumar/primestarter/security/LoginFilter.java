package org.kumar.primestarter.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@WebFilter(urlPatterns = { "/", "/login.xhtml" })
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
        HttpServletResponse httpServletRespone = ((HttpServletResponse) response);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // user is not logged in
        if (auth instanceof AnonymousAuthenticationToken) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.xhtml");
            dispatcher.forward(request, response);
        }
        // user is logged in
        else {
            httpServletRespone.sendRedirect("dashboard.xhtml");
        }
    }

}
