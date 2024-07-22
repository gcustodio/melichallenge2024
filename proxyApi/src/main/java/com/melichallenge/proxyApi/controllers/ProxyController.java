package com.melichallenge.proxyApi.controllers;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.melichallenge.proxyApi.services.LoggerService;
import com.melichallenge.proxyApi.services.StatsService;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StatsService statsService;

    @Autowired
    private LoggerService loggerService;

    private final String TARGET_URL = "https://jsonplaceholder.typicode.com"; // TODO: deberia estar en un secret de vault por ejemplo

    @GetMapping("/**")
    public ResponseEntity<?> proxyRequest(HttpServletRequest request) {
        String uri = request.getRequestURI().replace("/proxy", "");
        statsService.recordRequest(request.getRemoteAddr(), uri); // Metrica para contar cantidad de llamados
        loggerService.logRequest(request);

        // TODO: llamar al RateLimitingFilter.doFilterInternal(...)

        ResponseEntity<String> response = restTemplate.exchange(TARGET_URL + uri, HttpMethod.GET, null, String.class);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
