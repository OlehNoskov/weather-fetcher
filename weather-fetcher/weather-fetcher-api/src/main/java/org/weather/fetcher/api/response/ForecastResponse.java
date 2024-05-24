package org.weather.fetcher.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForecastResponse {
    @JsonProperty("location")
    private LocationResponse locationResponse;
    @JsonProperty("current")
    private WeatherResponse weatherResponse;
}
