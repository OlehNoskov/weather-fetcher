package com.service.core.controller;

import com.service.core.dto.response.ForecastResponse;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;

import java.text.ParseException;

@Path("/weather")
public interface WeatherController {

    @GET
    @Path("/countries/{country}/cities/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    ForecastResponse getWeatherByCityAndCountry(@PathParam("country") String country,
                                                @PathParam("city") String city) throws ParseException;
}
