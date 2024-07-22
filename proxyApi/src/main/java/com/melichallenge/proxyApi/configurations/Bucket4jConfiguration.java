package com.melichallenge.proxyApi.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class Bucket4jConfiguration {

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    private final int MAX_REQUEST_IP = 2;

    public boolean isRequestAllowed(String ip, String path) {
        // TODO Implementar lógica de rate limiting
        // TODO Usar Redis para obtener info por ejemplo: ip, cantidad de llamados x min; path, cantidad de llamados x min;

        // TODO utilizar Bucket4j para filtrar / controlar los requests

        // TODO agregar metricas: cantidad de llamados permitidos, cantidad de llamados no permitidos.

        return true;
    }
}
