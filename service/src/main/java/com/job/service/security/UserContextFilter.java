package com.job.service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)throws ServletException, IOException {
        System.out.println("Applicarion flow came to doFilterInternal");
        String username = request.getHeader("X-User-Name");
        String rolesHeader = request.getHeader("X-User-Roles");
        if(username != null && rolesHeader != null){
            Set<String> roles = Set.of(rolesHeader.split(","))
                    .stream()
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .collect(Collectors.toSet());
            UserContextHolder.set(new UserContext(username, roles));
        }
        chain.doFilter(request, response);
        UserContextHolder.clear();
    }
}
