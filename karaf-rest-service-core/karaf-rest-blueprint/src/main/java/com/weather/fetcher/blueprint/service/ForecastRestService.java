package com.weather.fetcher.blueprint.service;

import com.weather.fetcher.api.model.Forecast;
import com.weather.fetcher.api.service.ForecastService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

@Path("/")
//@AllArgsConstructor
public class ForecastRestService implements ForecastService {

//    private final WeatherClientService weatherClientService;

    @Override
    @Path("/countries/{country}/cities/{city}")
    @Produces("application/json")
    @GET
    public Forecast getForecast(@PathParam("country") String country,
                                @PathParam("city") String city) throws IOException, URISyntaxException, InterruptedException, ParseException {

        return ForecastResponseService.getForecast(country, city);
    }
}
