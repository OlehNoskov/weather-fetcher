package com.service.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherRequest {
    @JsonProperty("last_updated")
    private String updateDate;
    @JsonProperty("temp_c")
    private String temperature;
    @JsonProperty("condition")
    private OverallWeatherRequest overallWeatherRequest;
    private String humidity;
    private String cloud;
    @JsonProperty("uv")
    private String ultraviolet;
}
