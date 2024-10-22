package ma.sse.eas.capstoneproject.security;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtInterceptingFilter extends GenericFilterBean {

    public JwtInterceptingFilter() {}

    @Override
    public void doFilter(jakarta.servlet.ServletRequest servletRequest, jakarta.servlet.ServletResponse servletResponse, jakarta.servlet.FilterChain filterChain)
            throws IOException, jakarta.servlet.ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            Authentication authentication = JwtHelper.parse(httpRequest);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(servletRequest, servletResponse);
    }
}
