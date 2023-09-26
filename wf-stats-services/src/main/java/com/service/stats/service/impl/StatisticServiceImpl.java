package com.service.stats.service.impl;

import com.service.stats.entity.Forecast;
import com.service.stats.entity.Statistic;
import com.service.stats.service.StatisticService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final EntityManager entityManager;
    private static final String DATA_FIELD = "data";
    private static final String COUNT_FIELD = "count";
    private static final String CITY_FIELD = "city";
    private static final String SPACE = " ";
    private static final int LIMIT_RESULT = 5;

    @Override
    public List<Statistic> getStatistic(String field) {
        CriteriaQuery<Statistic> query = getCriteriaQuery();
        Root<Forecast> forecastRoot = query.from(Forecast.class);

        query.multiselect(
                        getExpression(forecastRoot, field).alias(DATA_FIELD),
                        getCountExpression(forecastRoot).alias(COUNT_FIELD))
                .groupBy(getExpression(forecastRoot, field))
                .orderBy(getCriteriaBuilder().desc(getCountExpression(forecastRoot)));

        return getStatistics(query);
    }

    @Override
    public List<Statistic> getStatisticByInterval(String field, String interval) {
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

    @Override
    public List<Statistic> getCitiesByCountryStatistic(String country) {
        CriteriaQuery<Statistic> query = getCriteriaQuery();
        Root<Forecast> forecastRoot = query.from(Forecast.class);

        query.multiselect(
                        getExpression(forecastRoot, CITY_FIELD).alias(DATA_FIELD),
                        getCountExpression(forecastRoot).alias(COUNT_FIELD))
                .where(getCountryPredicate(getCriteriaBuilder(), forecastRoot, country))
                .groupBy(getExpression(forecastRoot, CITY_FIELD))
                .orderBy(getCriteriaBuilder().desc(getCountExpression(forecastRoot)));

        return getStatistics(query);
    }

    @Override
    public List<Statistic> getCitiesByCountryAndIntervalStatistic(String country, String interval) {
        CriteriaQuery<Statistic> query = getCriteriaQuery();
        Root<Forecast> forecastRoot = query.from(Forecast.class);

        query.multiselect(
                        getExpression(forecastRoot, CITY_FIELD).alias(DATA_FIELD),
                        getCountExpression(forecastRoot).alias(COUNT_FIELD))
                .where(getCriteriaBuilder().and(getCountryPredicate(getCriteriaBuilder(), forecastRoot, country),
                        getDatePredicate(getCriteriaBuilder(), forecastRoot, getInterval(interval))))
                .groupBy(getExpression(forecastRoot, CITY_FIELD))
                .orderBy(getCriteriaBuilder().desc(getCountExpression(forecastRoot)));

        return getStatistics(query);
    }

    private List<Statistic> getStatistics(CriteriaQuery<Statistic> query) {
        TypedQuery<Statistic> typedQuery = entityManager.createQuery(query);
        typedQuery.setMaxResults(LIMIT_RESULT);

        return typedQuery.getResultList().isEmpty() ? List.of() : typedQuery.getResultList();
    }

    private Date getInterval(String interval) {
        Calendar calendar = Calendar.getInstance();
        int amount = Integer.parseInt(interval.split(SPACE)[0]);
        String period = interval.split(SPACE)[1];

        if (period.equals("WEEK")) {
            calendar.add(Calendar.WEEK_OF_YEAR, -amount);
        }
        if (period.equals("MONTH")) {
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
}
