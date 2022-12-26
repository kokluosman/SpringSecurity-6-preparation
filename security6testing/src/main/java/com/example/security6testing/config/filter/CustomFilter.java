package com.example.security6testing.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
@Slf4j
public class CustomFilter extends GenericFilterBean {

    private final BasicAuthenticationConverter authenticationConverter= new BasicAuthenticationConverter();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        UsernamePasswordAuthenticationToken authenticationToken =
                this.authenticationConverter.convert(httpServletRequest);
        final var name = authenticationToken.getName();
        final var method = httpServletRequest.getMethod();
        log.info("Following user:'{}' has sent request with method type of {}", name, method);
        chain.doFilter(request,response);
    }
}
