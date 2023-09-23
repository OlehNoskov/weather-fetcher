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
    private static final String COUNTRY_FIELD = "country";
    private static final String CITY_FIELD = "city";
    private static final String EMPTY_STRING = "";
    private static final String SPACE = " ";
    private static final int LIMIT_RESULT = 5;

    @Override
    public List<Statistic> getCountriesStatistic(String interval) {
        return getStatistics(getTypedQuery(interval, COUNTRY_FIELD, EMPTY_STRING));
    }

    @Override
    public List<Statistic> getCitiesByCountryStatistic(String country, String interval) {
        return getStatistics(getTypedQuery(interval, CITY_FIELD, country));
    }

    private TypedQuery<Object[]> getTypedQuery(String interval, String expression, String country) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<Forecast> forecastRoot = query.from(Forecast.class);

        Expression<String> fieldExpression = forecastRoot.get(expression);
        Expression<Long> countExpression = criteriaBuilder.count(forecastRoot);

        if (!Objects.equals(interval, DateInterval.getFromString(interval).getText())) {
            if (isEmptyCountry(country)) {
                query.multiselect(fieldExpression.alias(DATA_FIELD), countExpression.alias(COUNT_FIELD))
                        .groupBy(fieldExpression)
                        .orderBy(criteriaBuilder.desc(countExpression));
            } else {
                query.multiselect(fieldExpression.alias(DATA_FIELD), countExpression.alias(COUNT_FIELD))
                        .where(getCountryPredicate(criteriaBuilder, forecastRoot, country))
                        .groupBy(fieldExpression)
                        .orderBy(criteriaBuilder.desc(countExpression));
            }
        } else {
            if (!isEmptyCountry(country)) {
                query.multiselect(fieldExpression.alias(DATA_FIELD), countExpression.alias(COUNT_FIELD))
                        .where(criteriaBuilder.and(getCountryPredicate(criteriaBuilder, forecastRoot, country),
                                getDatePredicate(criteriaBuilder, forecastRoot, getInterval(interval))))
                        .groupBy(fieldExpression)
                        .orderBy(criteriaBuilder.desc(countExpression));
            } else {
                query.multiselect(fieldExpression.alias(DATA_FIELD), countExpression.alias(COUNT_FIELD))
                        .where(getDatePredicate(criteriaBuilder, forecastRoot, getInterval(interval)))
                        .groupBy(fieldExpression)
                        .orderBy(criteriaBuilder.desc(countExpression));
            }
        }
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
        typedQuery.setMaxResults(LIMIT_RESULT);

        return typedQuery;
    }

    private List<Statistic> getStatistics(TypedQuery<Object[]> typedQuery) {
        return typedQuery.getResultList().isEmpty() ? List.of() : typedQuery.getResultList().stream()
                .map(result -> new Statistic((String) result[0], (Long) result[1])).toList();
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

    private Predicate getDatePredicate(CriteriaBuilder criteriaBuilder, Root<Forecast> forecastRoot, Date thresholdDate) {
        return criteriaBuilder.greaterThanOrEqualTo(forecastRoot.get("date"), thresholdDate);
    }

    private Predicate getCountryPredicate(CriteriaBuilder criteriaBuilder, Root<Forecast> forecastRoot, String country) {
        return criteriaBuilder.equal(forecastRoot.get(COUNTRY_FIELD), country);
    }

    private boolean isEmptyCountry(String country) {
        return Objects.equals(country, EMPTY_STRING) || Objects.equals(country, null);
    }
}
