package com.service.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.core.entity.Forecast;
import com.service.core.entity.Temperature;
import com.service.core.entity.Weather;
import com.service.core.service.ForecastService;
import com.service.core.util.enums.Scale;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.service.core.util.constants.WeatherFields.*;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    public Forecast getForecast(String response) throws JsonProcessingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);

        Temperature temperature = new Temperature();
        temperature.setScale(Scale.CELSIUS);
        temperature.setDegrees(jsonNode.get(CURRENT).get(TEMPERATURE).asInt());

        Weather weather = new Weather();
        weather.setOverall(jsonNode.get(CURRENT).get(CONDITION).get(TEXT).asText().toUpperCase());
        weather.setTemperature(temperature);

        Forecast forecast = new Forecast();
        forecast.setCountry(jsonNode.get(LOCATION).get(COUNTRY).asText());
        forecast.setCity(jsonNode.get(LOCATION).get(CITY).asText());
        forecast.setDate(getFormattedDate(jsonNode.get(CURRENT).get(DATE).asText()));
        forecast.setWeather(weather);
        return forecast;
    }

    private Date getFormattedDate(String date) throws ParseException {
        return DATE_FORMAT.parse(date);
    }
}
