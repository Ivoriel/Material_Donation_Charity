package pl.kosinski.charity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        var userLoggedIn = session.getAttribute("userLoggedIn");
        if (userLoggedIn == null) {
            session.setAttribute("userLoggedIn", false);
            userLoggedIn = false;
        }
        if (! (boolean) userLoggedIn && ! ((HttpServletRequest) request).getRequestURI().equals("/user/login")) {
            ((HttpServletResponse) response).sendRedirect("/user/login");
        } else {
            filterChain.doFilter(request, response);
        }
    }


}
