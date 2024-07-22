package com.melichallenge.proxyApi.services;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class StatsService {
    private Map<String, Integer> statistics = new ConcurrentHashMap<>();

    public void recordRequest(String ip, String path) {
        String key = ip + "|" + path;
        statistics.put(key, statistics.getOrDefault(key, 0) + 1);
    }

    public Map<String, Object> getStatistics() {
        return Collections.unmodifiableMap(statistics);
    }
}
