package com.example.SmartFarmingMonitor.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String url = "https://api.open-meteo.com/v1/forecast?latitude=44.65&longitude=-63.57&current_weather=true&timezone=America/Halifax";

    public Map<String, Object> getCurrentWeather() {
        RestTemplate restTemplate = new RestTemplate(); // http request
        Map<String, Object> response = restTemplate.getForObject(url, Map.class); // resttemplate does get request on url. get puts json data in map.class. map.class is cast to map resoponse
        return response;
    }

}