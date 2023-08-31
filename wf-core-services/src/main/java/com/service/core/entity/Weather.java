package com.service.core.entity;

import com.service.core.util.enums.OverallWeather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Weather {
    private OverallWeather overall;
    private Temperature temperature;
}
