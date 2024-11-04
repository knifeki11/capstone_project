package ma.sse.eas.capstoneproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtInterceptingFilter extends GenericFilterBean {

    public JwtInterceptingFilter() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) ((HttpServletRequestWrapper) servletRequest).getRequest();

        Authentication authentication = JwtHelper.parse(httpRequest);

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
