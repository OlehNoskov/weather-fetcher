package com.service.core.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.core.response.ForecastResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {
    public static String WEATHER_BASE_URL = "https://api.weatherapi.com";
    public static String WEATHER_URL = "/v1/current.json?key=";
    public static String WEATHER_KEY = "b8ea3a60088042cb99a75755231109";
    public static String QUERY_DELIMITER = "&q=";

    public ForecastResponse getForecast(String country, String city) throws IOException, InterruptedException, URISyntaxException {
        String url = WEATHER_BASE_URL + WEATHER_URL + WEATHER_KEY + QUERY_DELIMITER + country + QUERY_DELIMITER + city;

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest request = HttpRequest.newBuilder(new URI(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.body(), ForecastResponse.class);
    }
}

