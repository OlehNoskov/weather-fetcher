package com.service.core.controller;

import com.service.core.entity.Forecast;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@Path("/weather")
public interface WeatherController {

//    @GET
//    @Path("/countries/{country}/cities/{city}")
//    @Produces(MediaType.APPLICATION_JSON)
//    Forecast getWeatherByCityAndCountry(@PathParam("country") String country,
//                                        @PathParam("city") String city) throws ParseException;
    @GET
    @Path("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    Forecast getWeatherByCityAndCountry() throws ParseException;
}
