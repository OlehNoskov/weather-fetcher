package com.service.core.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForecastRequest {
    @JsonProperty("location")
    private LocationRequest locationRequest;
    @JsonProperty("current")
    private WeatherRequest weatherRequest;
}
