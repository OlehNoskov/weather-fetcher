package com.service.core.service;

import com.service.core.entity.Forecast;
import jakarta.jws.WebService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.text.ParseException;

//@Path("/")
@WebService
public interface ForecastService {
//    @GET
//    @Path("/weather/countries/{country}/cities/{city}")
    Forecast getForecast(String country, @PathParam("city") String city) throws ParseException;
}
