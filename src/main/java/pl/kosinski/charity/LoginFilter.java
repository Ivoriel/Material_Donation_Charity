package pl.kosinski.charity;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        var userLoggedIn = session.getAttribute("userLoggedIn");
        if (userLoggedIn == null) {
            session.setAttribute("userLoggedIn", false);
            userLoggedIn = false;
        }
        filterChain.doFilter(request, response);
//        if (! (boolean) userLoggedIn) {
//            ((HttpServletResponse) response).sendRedirect("/charity/login");
//            break;
//        }
    }
}
