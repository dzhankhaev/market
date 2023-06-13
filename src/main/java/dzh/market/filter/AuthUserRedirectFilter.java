package dzh.market.filter;

import dzh.market.BaseRoutes;
import dzh.market.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class AuthUserRedirectFilter extends GenericFilterBean
{
    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;


        if (userService.isAuthenticated() &&
                (BaseRoutes.LOGIN.equals(servletRequest.getRequestURI()) || BaseRoutes.REGISTRATION.equals(servletRequest.getRequestURI())))
        {
            String encodedRedirectURL = ((HttpServletResponse) response).encodeRedirectURL(servletRequest.getContextPath() + BaseRoutes.ROOT);

            servletResponse.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
            servletResponse.setHeader(HttpHeaders.LOCATION, encodedRedirectURL);
        }

        chain.doFilter(servletRequest, servletResponse);
    }
}
