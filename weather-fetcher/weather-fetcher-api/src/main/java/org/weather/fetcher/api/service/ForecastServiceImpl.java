package org.weather.fetcher.api.service;

import lombok.AllArgsConstructor;
import org.weather.fetcher.api.model.ForecastModel;
import org.weather.fetcher.api.response.ForecastResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
public class ForecastServiceImpl implements ForecastService {

//    private final Repository repository;

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    public ForecastModel getForecast(String country, String city) throws IOException, URISyntaxException, InterruptedException, ParseException {

        ForecastResponse forecastResponse = WeatherClientService.getForecast(country, city);
        ForecastModel forecastModel = ForecastModel.builder()
                .country(forecastResponse.getLocationResponse().getCountry())
                .city(forecastResponse.getLocationResponse().getCity())
                .date(getDate(forecastResponse))
                .overall(forecastResponse.getWeatherResponse().getOverallWeatherResponse().getOverallWeather())
                .scale("CELSIUS")
                .degrees((int) Math.round(Double.parseDouble(forecastResponse.getWeatherResponse().getTemperature())))
                .build();

//        repository.saveForecast(ForecastModelToForecastEntityMapper.getForecastEntityFromForecastModel(forecastModel));

        return forecastModel;
    }


    private Date getDate(ForecastResponse forecastResponse) throws ParseException {
        return DATE_FORMAT.parse(forecastResponse.getWeatherResponse().getUpdateDate());
    }
}
