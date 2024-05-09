package com.service.core.response;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class ForecastResponse {
    @JsonProperty("location")
    private LocationResponse locationResponse;
    @JsonProperty("current")
    private WeatherResponse weatherResponse;
}
