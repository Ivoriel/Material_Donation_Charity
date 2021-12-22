package pl.kosinski.charity;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Component
//public class LoginFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//
////        HttpSession session = ((HttpServletRequest) request).getSession();
////        Boolean userLoggedIn = false;
////        if (session.getAttribute("userLoggedIn") != null) {
////            userLoggedIn = (Boolean) session.getAttribute("userLoggedIn");
////        }
////        if (!userLoggedIn) {
////            ((HttpServletResponse) response).sendRedirect("/charity/login");
////        }
//    }
//}
