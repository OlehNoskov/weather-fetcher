package com.weather.fetcher.blueprint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OverallWeatherResponse {
    @JsonProperty("text")
    private String overallWeather;
}
