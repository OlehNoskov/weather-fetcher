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

import javax.print.DocFlavor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.service.core.util.constants.WeatherFields.*;


@Service
public class ForecastServiceImpl implements ForecastService {
    @Override
    public Forecast getForecast(String response) throws JsonProcessingException, ParseException {
        Forecast forecast = new Forecast();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        forecast.setCountry(jsonNode.get(LOCATION).get(COUNTRY).asText());
        forecast.setCity(jsonNode.get(LOCATION).get(CITY).asText());
        forecast.setDate(getFormattedDate(jsonNode.get(CURRENT).get(DATE).asText()));
        forecast.setWeather(new Weather(jsonNode.get(CURRENT).get(CONDITION).get(TEXT).asText().toUpperCase(),
                new Temperature(Scale.CELSIUS, jsonNode.get(CURRENT).get(TEMPERATURE).asInt())));
        return forecast;
    }

    private Date getFormattedDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date1 = df.parse(date);
        DateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy hh:mm aa");
        System.out.println(dateFormat2.format(date1));
        System.out.println(dateFormat2.parse(dateFormat2.format(date1)));
        return dateFormat2.parse(dateFormat2.format(date1));
    }
}
