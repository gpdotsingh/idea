package com.idea.loadOnLogin.idea.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component


public class JwtRequestFilter  extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            System.out.println("State "+  httpServletRequest.getAttribute("state"));
        System.out.println("code+ "+ httpServletRequest.getAttribute("code"));
    }
}
