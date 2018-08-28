package pl.coderslab.preschool_web_service.filter;

import javax.servlet.*;
import java.io.IOException;

public class ConfirmDeleteUser implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        System.out.println("Update delete log");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
