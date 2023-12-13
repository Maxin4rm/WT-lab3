package wt3.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.util.Optional;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String url = "";
        if(httpServletRequest.getRequestURI() != null){
            url = httpServletRequest.getRequestURI();
        }
        //String url = Optional.ofNullable(httpServletRequest.getRequestURI()).orElse("");
        if (!url.contains("auth") && httpServletRequest.getSession().getAttribute("user") == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/auth/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
