package com.weather.fetcher.api.service;

import com.weather.fetcher.api.enums.OverallWeather;
import com.weather.fetcher.api.enums.Scale;
import com.weather.fetcher.api.model.ForecastModel;
import com.weather.fetcher.api.model.TemperatureModel;
import com.weather.fetcher.api.model.WeatherModel;
import com.weather.fetcher.api.response.ForecastResponse;

import javax.ws.rs.Path;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/")
public class ForecastServiceImpl implements ForecastService {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    public ForecastModel getForecast(String country, String city) throws IOException, URISyntaxException, InterruptedException, ParseException {

        ForecastResponse forecastResponse = WeatherClientService.getForecast(country, city);
        ForecastModel forecastModel = ForecastModel.builder()
                .country(forecastResponse.getLocationResponse().getCountry())
                .city(forecastResponse.getLocationResponse().getCity())
                .date(getDate(forecastResponse))
                .weatherModel(getWeather(forecastResponse))
                .build();

//        repository.saveForecast(ForecastModelToForecastEntityMapper.getForecastEntityFromForecastModel(forecastModel));

        return forecastModel;
    }

    private WeatherModel getWeather(ForecastResponse forecast) {
        TemperatureModel temperatureModel = TemperatureModel
                .builder()
                .scale(Scale.CELSIUS)
                .degrees((int) Math.round(Double.parseDouble(forecast.getWeatherResponse().getTemperature())))
                .build();

//        repository.saveTemperature(TemperatureModelToTemperatureEntityMapper.getTemperatureEntityFromTemperatureModel(temperatureModel));

        WeatherModel weatherModel = WeatherModel
                .builder()
                .overall(OverallWeather.getFromString(forecast
                        .getWeatherResponse()
                        .getOverallWeatherResponse()
                        .getOverallWeather()))
                .temperatureModel(temperatureModel)
                .build();

//        repository.saveWeather(WeatherModelToWeatherEntityMapper.getWeatherEntityFromWeatherModel(weatherModel));

        return weatherModel;
    }

    private Date getDate(ForecastResponse forecastResponse) throws ParseException {
        return DATE_FORMAT.parse(forecastResponse.getWeatherResponse().getUpdateDate());
    }
}
