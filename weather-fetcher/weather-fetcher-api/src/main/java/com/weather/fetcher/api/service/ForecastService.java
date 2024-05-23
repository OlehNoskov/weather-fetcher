package com.weather.fetcher.api.service;

import com.weather.fetcher.api.model.ForecastModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

@Path("/")
public interface ForecastService {
    @Path("/countries/{country}/cities/{city}")
    @Produces("application/json")
    @GET
    ForecastModel getForecast(@PathParam("country") String country,
                              @PathParam("city") String city) throws IOException, URISyntaxException, InterruptedException, ParseException;
}
