package com.service.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OverallWeatherRequest {
    @JsonProperty("text")
    private String overallWeather;
}
