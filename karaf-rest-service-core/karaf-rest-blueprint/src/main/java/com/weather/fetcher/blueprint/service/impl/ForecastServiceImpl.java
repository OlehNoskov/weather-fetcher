package com.weather.fetcher.blueprint.service.impl;

import com.weather.fetcher.api.model.ForecastModel;
import com.weather.fetcher.api.service.ForecastService;
import com.weather.fetcher.blueprint.service.ForecastResponseService;
import lombok.AllArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

@Path("/")
@AllArgsConstructor
public class ForecastServiceImpl implements ForecastService {

    private final ForecastResponseService forecastResponseService;

    @Override
    @Path("/countries/{country}/cities/{city}")
    @Produces("application/json")
    @GET
    public ForecastModel getForecast(@PathParam("country") String country,
                                     @PathParam("city") String city) throws IOException, URISyntaxException, InterruptedException, ParseException {

        return forecastResponseService.getForecast(country, city);
    }
}
