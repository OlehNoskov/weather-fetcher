package com.weather.fetcher.blueprint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {
    @JsonProperty("last_updated")
    private String updateDate;
    @JsonProperty("temp_c")
    private String temperature;
    @JsonProperty("condition")
    private OverallWeatherResponse overallWeatherResponse;
    private String humidity;
    private String cloud;
    @JsonProperty("uv")
    private String ultraviolet;
}
