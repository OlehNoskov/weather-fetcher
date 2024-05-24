package org.weather.fetcher.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForecastModel {
    private String country;
    private String city;
    private Date date;
    private String overall;
    private String scale;
    private int degrees;
}

