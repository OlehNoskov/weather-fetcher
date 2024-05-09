package com.service.core.api;

import com.service.core.model.Forecast;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/weather")
public interface WeatherController {

    @GET
    @Path("/countries/{country}/cities/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    Forecast getWeatherByCityAndCountry(@PathParam("country") String country,
                                        @PathParam("city") String city) throws ParseException, IOException, URISyntaxException, InterruptedException;
}
