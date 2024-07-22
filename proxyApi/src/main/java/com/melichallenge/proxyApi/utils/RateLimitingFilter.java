package com.melichallenge.proxyApi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.melichallenge.proxyApi.configurations.Bucket4jConfiguration;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @Autowired
    private Bucket4jConfiguration bucket4jConfig;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private FilterChain filterChain;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String ip = request.getRemoteAddr();
        String path = request.getRequestURI();

        boolean allowed = bucket4jConfig.isRequestAllowed(ip, path);

        if (!allowed) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); // 429
            response.getWriter().write("Too many requests"); // TODO: Crear un objeto para la estandarizacion de los response
            return;
        }

        filterChain.doFilter(request, response);
    }

}
