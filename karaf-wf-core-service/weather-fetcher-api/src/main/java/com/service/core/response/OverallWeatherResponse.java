package com.service.core.response;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class OverallWeatherResponse {
    @JsonProperty("text")
    private String overallWeather;
}
