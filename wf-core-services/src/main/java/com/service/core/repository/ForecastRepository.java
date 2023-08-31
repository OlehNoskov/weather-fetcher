package com.service.core.repository;

import com.service.core.entity.Forecast;
import org.springframework.data.repository.CrudRepository;

public interface ForecastRepository extends CrudRepository<Forecast, Long> {
}
