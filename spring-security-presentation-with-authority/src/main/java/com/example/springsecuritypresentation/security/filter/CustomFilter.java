package com.example.springsecuritypresentation.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
@Slf4j
public class CustomFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter =
                new UsernamePasswordAuthenticationFilter();
        final var usernameParameter = usernamePasswordAuthenticationFilter.getUsernameParameter();
        final var method = servletRequest.getMethod();

        log.info(
                "Following user:{} has sent request with method type of {}", usernameParameter, method
        );
        chain.doFilter(request,response);
    }


}
