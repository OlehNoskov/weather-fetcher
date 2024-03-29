package com.service.stats.service.impl;

import com.service.stats.entity.Forecast;
import com.service.stats.entity.Statistic;
import com.service.stats.enums.DateInterval;
import com.service.stats.service.StatisticService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final EntityManager entityManager;
    private static final String DATA_FIELD = "data";
    private static final String COUNT_FIELD = "count";
    private static final String SPACE = " ";

    @Override
    public List<Statistic> getWeatherStatistic(String field, String country, String interval) {
        if (isEmptyCountry(country) && isValidInterval(interval)) {
            return getStatisticByInterval(field, interval);
        }

        if (!isEmptyCountry(country)) {
            return !isValidInterval(interval)
                    ? getCitiesByCountryStatistic(field, country)
                    : getCitiesByCountryAndIntervalStatistic(field, country, interval);
        }

        CriteriaQuery<Statistic> query = getCriteriaQuery();
        Root<Forecast> forecastRoot = query.from(Forecast.class);
        query.multiselect(
                        getExpression(forecastRoot, field).alias(DATA_FIELD),
                        getCountExpression(forecastRoot).alias(COUNT_FIELD))
                .groupBy(getExpression(forecastRoot, field))
                .orderBy(getCriteriaBuilder().desc(getCountExpression(forecastRoot)));

        return getStatistics(query);
    }

    private List<Statistic> getStatisticByInterval(String field, String interval) {
        CriteriaQuery<Statistic> query = getCriteriaQuery();
        Root<Forecast> forecastRoot = query.from(Forecast.class);

        query.multiselect(
                        getExpression(forecastRoot, field).alias(DATA_FIELD),
                        getCountExpression(forecastRoot).alias(COUNT_FIELD))
                .where(getDatePredicate(getCriteriaBuilder(), forecastRoot, getInterval(interval)))
                .groupBy(getExpression(forecastRoot, field))
                .orderBy(getCriteriaBuilder().desc(getCountExpression(forecastRoot)));

        return getStatistics(query);
    }

    private List<Statistic> getCitiesByCountryStatistic(String field, String country) {
        CriteriaQuery<Statistic> query = getCriteriaQuery();
        Root<Forecast> forecastRoot = query.from(Forecast.class);

        query.multiselect(
                        getExpression(forecastRoot, field).alias(DATA_FIELD),
                        getCountExpression(forecastRoot).alias(COUNT_FIELD))
                .where(getCountryPredicate(getCriteriaBuilder(), forecastRoot, country))
                .groupBy(getExpression(forecastRoot, field))
                .orderBy(getCriteriaBuilder().desc(getCountExpression(forecastRoot)));

        return getStatistics(query);
    }

    private List<Statistic> getCitiesByCountryAndIntervalStatistic(String field, String country, String interval) {
        CriteriaQuery<Statistic> query = getCriteriaQuery();
        Root<Forecast> forecastRoot = query.from(Forecast.class);

        query.multiselect(
                        getExpression(forecastRoot, field).alias(DATA_FIELD),
                        getCountExpression(forecastRoot).alias(COUNT_FIELD))
                .where(getCriteriaBuilder().and(getCountryPredicate(getCriteriaBuilder(), forecastRoot, country),
                        getDatePredicate(getCriteriaBuilder(), forecastRoot, getInterval(interval))))
                .groupBy(getExpression(forecastRoot, field))
                .orderBy(getCriteriaBuilder().desc(getCountExpression(forecastRoot)));

        return getStatistics(query);
    }

    private List<Statistic> getStatistics(CriteriaQuery<Statistic> query) {
        int limitResult = 5;
        TypedQuery<Statistic> typedQuery = entityManager.createQuery(query);
        typedQuery.setMaxResults(limitResult);

        return typedQuery.getResultList().isEmpty() ? List.of() : typedQuery.getResultList();
    }

    private Date getInterval(String interval) {
        Calendar calendar = Calendar.getInstance();
        int amount = Integer.parseInt(interval.split(SPACE)[0]);
        String period = interval.split(SPACE)[1].toLowerCase();

        if (period.equals("week")) {
            calendar.add(Calendar.WEEK_OF_YEAR, -amount);
        }
        if (period.equals("month")) {
            calendar.add(Calendar.MONTH, -amount);
        }
        return calendar.getTime();
    }

    private CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    private CriteriaQuery<Statistic> getCriteriaQuery() {
        return getCriteriaBuilder().createQuery(Statistic.class);
    }

    private Predicate getDatePredicate(CriteriaBuilder criteriaBuilder, Root<Forecast> forecastRoot, Date thresholdDate) {
        return criteriaBuilder.greaterThanOrEqualTo(forecastRoot.get("date"), thresholdDate);
    }

    private Predicate getCountryPredicate(CriteriaBuilder criteriaBuilder, Root<Forecast> forecastRoot, String country) {
        return criteriaBuilder.equal(forecastRoot.get("country"), country);
    }

    private Expression<String> getExpression(Root<Forecast> forecastRoot, String expression) {
        return forecastRoot.get(expression);
    }

    private Expression<Long> getCountExpression(Root<Forecast> forecastRoot) {
        return getCriteriaBuilder().count(forecastRoot);
    }

    private boolean isValidInterval(String interval) {
        return !DateInterval.getFromString(interval).equals(DateInterval.UNKNOWN);
    }

    private boolean isEmptyCountry(String country) {
        return Objects.equals("", country) || Objects.equals(null, country);
    }
}
