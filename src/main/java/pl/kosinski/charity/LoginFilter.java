package pl.kosinski.charity;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Component
//@WebFilter(urlPatterns = {"/institution/*", "/user/profile/*", "/user/list/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        var userLoggedIn = session.getAttribute("userLoggedIn");
        if (userLoggedIn == null) {
            session.setAttribute("userLoggedIn", false);
            userLoggedIn = false;
//        } else {
//            userLoggedIn = true;
        }
        if (! (boolean) userLoggedIn && ! ((HttpServletRequest) request).getRequestURI().equals("/user/login")) {
            ((HttpServletResponse) response).sendRedirect("/user/login");
        } else {
            filterChain.doFilter(request, response);
        }
    }


}
