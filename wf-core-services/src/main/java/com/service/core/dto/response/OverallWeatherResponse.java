package com.service.core.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OverallWeatherResponse {
    @JsonProperty("text")
    private String overallWeather;
}
