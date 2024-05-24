package org.weather.fetcher.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherModel {
    private String overall;
    @JsonProperty("temperature")
    private TemperatureModel temperatureModel;
}
