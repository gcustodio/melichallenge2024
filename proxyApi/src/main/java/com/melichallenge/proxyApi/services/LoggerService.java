package com.melichallenge.proxyApi.services;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    private static final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    public void logRequest(HttpServletRequest request) {
        logger.info("Request received: IP - {}, Path - {}", request.getRemoteAddr(), request.getRequestURI());
    }

    // TODO optimizar metodo de loggeo. Para loggear errores tambien
}
