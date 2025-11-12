package com.jay.virtualthread.tripplanner.client;

import com.jay.virtualthread.tripplanner.dto.Weather;
import org.springframework.web.client.RestClient;

public class WeatherServiceClient {

    private final RestClient restClient;

    public WeatherServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public Weather getWeather(String airportCode) {
        return this.restClient.get()
                              .uri("{airportCode}", airportCode)
                              .retrieve()
                              .body(Weather.class);
    }

}
