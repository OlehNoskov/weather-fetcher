package org.weather.fetcher.api.mappers;

import org.weather.fetcher.api.model.ForecastModel;
import org.weather.fetcher.repo.model.Forecast;

public class ForecastModelToForecastEntityMapper {

    public static Forecast getForecastEntityFromForecastModel(ForecastModel forecastModel) {
        return Forecast
                .builder()
                .country(forecastModel.getCountry())
                .city(forecastModel.getCity())
                .date(forecastModel.getDate())
                .overall(forecastModel.getOverall())
                .scale(forecastModel.getScale())
                .degrees(forecastModel.getDegrees())
                .build();
    }
}
