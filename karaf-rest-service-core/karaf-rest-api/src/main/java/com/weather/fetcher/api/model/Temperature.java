package com.weather.fetcher.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weather.fetcher.api.enums.Scale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Temperature {
    @JsonIgnore
    private Long id;
    private Scale scale;
    private int degrees;
}
