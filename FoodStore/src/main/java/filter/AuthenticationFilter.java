/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 *
 * @author truon
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Cast to HttpServletRequest and HttpServletResponse
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Get the session and check if user is logged in
        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        // Get the requested URI
        String uri = req.getRequestURI();

        // Allow access to login and registration pages without authentication
        if (uri.endsWith("login.jsp") || uri.endsWith("register.jsp") || uri.endsWith("LoginServlet") || uri.endsWith("RegisterServlet")) {
            chain.doFilter(request, response);
            return;
        }

        if (loggedIn) {
            // User is logged in, proceed to requested resource
            chain.doFilter(request, response);
        } else {
            // User is not logged in, redirect to login page
            res.sendRedirect("login.jsp");
        }
    }

    public void destroy() {
        // Cleanup code if needed
    }
}
