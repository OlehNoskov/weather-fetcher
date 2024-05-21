package com.weather.fetcher.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.fetcher.api.enums.OverallWeather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherModel {
    private OverallWeather overall;
    @JsonProperty("temperature")
    private TemperatureModel temperatureModel;
}
