package com.service.core.controller;

import com.service.core.entity.Forecast;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.text.ParseException;

@Path("/weather")
public interface WeatherController {

    @GET
    @Path("/countries/{country}/cities/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    Forecast getWeatherByCityAndCountry(@PathParam("country") String country,
                                        @PathParam("city") String city) throws ParseException;
}
