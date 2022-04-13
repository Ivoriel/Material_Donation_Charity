package pl.kosinski.charity;

import pl.kosinski.user.UserDto;
import pl.kosinski.user.UserType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        var user = (UserDto) session.getAttribute("user");
        if (user.getUserType() != UserType.ADMIN) {
            ((HttpServletResponse) response).sendRedirect("/charity");
        } else {
            filterChain.doFilter(request, response);
        }
    }


}
