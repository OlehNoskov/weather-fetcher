package com.service.core.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForecastDto {
    @JsonProperty("location")
    private LocationDto locationDto;
    @JsonProperty("current")
    private WeatherDto weatherDto;
}
