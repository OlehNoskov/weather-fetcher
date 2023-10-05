package com.service.stats.enums;

import lombok.Getter;

@Getter
public enum DateInterval {
    ONE_WEEK("1 WEEK"),

    TWO_WEEK("2 WEEK"),

    ONE_MONTH("1 MONTH"),

    THREE_MONTH("3 MONTH"),

    UNKNOWN("UNKNOWN");

    private final String text;


    DateInterval(String text) {
        this.text = text;
    }

    public static DateInterval getFromString(String dateInterval) {
        for (DateInterval interval : DateInterval.values()) {
            if (interval.text.equalsIgnoreCase(dateInterval)) {
                return interval;
            }
        }
        return UNKNOWN;
    }
}
