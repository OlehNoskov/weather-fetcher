package com.service.core.service.impl;

import com.service.core.dto.response.ForecastDto;
import com.service.core.entity.Forecast;
import com.service.core.entity.Temperature;
import com.service.core.entity.Weather;
import com.service.core.service.ForecastService;
import com.service.core.service.WeatherClient;
import com.service.core.util.enums.OverallWeather;
import com.service.core.util.enums.Scale;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
@WebService(endpointInterface = "com.service.core.service.ForecastService", serviceName = "weather")
public class ForecastServiceImpl implements ForecastService {
    private final WeatherClient weatherClient;
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    @GET
    @Path("/weather/countries/{country}/cities/{city}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Forecast getForecast(@PathParam("country") String country,
                                @PathParam("city") String city) throws ParseException {
        ForecastDto forecastDto = weatherClient.getWeather(country, city);

        return Forecast
                .builder()
                .country(forecastDto.getLocationDto().getCountry())
                .city(forecastDto.getLocationDto().getCity())
                .date(DATE_FORMAT.parse(forecastDto.getWeatherDto().getUpdateDate()))
                .weather(getWeather(forecastDto))
                .build();
    }

    private Weather getWeather(ForecastDto forecastDto) {
        Temperature temperature = Temperature
                .builder()
                .scale(Scale.CELSIUS)
                .degrees((int) Math.round(Double.parseDouble(forecastDto.getWeatherDto().getTemperature())))
                .build();

        return Weather
                .builder()
                .overall(OverallWeather.getFromString(forecastDto
                        .getWeatherDto()
                        .getOverallWeatherDto()
                        .getOverallWeather()))
                .temperature(temperature)
                .build();
    }
}
