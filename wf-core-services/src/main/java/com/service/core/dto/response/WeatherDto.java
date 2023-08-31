package com.service.core.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherDto {
    @JsonProperty("last_updated")
    private String updateDate;
    @JsonProperty("temp_c")
    private String temperature;
    @JsonProperty("condition")
    private OverallWeatherDto overallWeatherDto;
    private String humidity;
    private String cloud;
    @JsonProperty("uv")
    private String ultraviolet;
}
