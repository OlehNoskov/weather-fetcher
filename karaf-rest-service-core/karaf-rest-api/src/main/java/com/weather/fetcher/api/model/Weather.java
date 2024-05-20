package com.weather.fetcher.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Weather {
    @JsonIgnore
    private Long id;
    private OverallWeather overall;
    @JsonProperty("temperature")
    private Temperature temperature;
}
