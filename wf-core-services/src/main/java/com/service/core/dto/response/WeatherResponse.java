package com.service.core.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.core.util.enums.OverallWeather;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponse {
    private OverallWeather overall;
    @JsonProperty("temperature")
    private TemperatureResponse temperature;
}
