package com.service.core.service;

import com.service.core.dto.request.ForecastRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather", url = "${weather.api}")
public interface WeatherClient {
    @RequestMapping(method = RequestMethod.GET,
            value = "/v1/current.json?key=${weather.key}&q={country}&q={city}")
    ForecastRequest getWeather(@RequestParam("country") String country,
                               @RequestParam("city") String city);
}
