package de.thab.sd19.demowebapp;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


@Component
public class RequestLoggingFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
        
        String uri = request.getRequestURI();
        System.out.println("request to " + uri + " at " + LocalDateTime.now());
    
        chain.doFilter(request, response);
    }
}
