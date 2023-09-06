package com.service.stats.enums;

public enum OverallWeather {
    SUNNY("Sunny"),
    CLOUDY("Cloudy"),
    PARTLY_CLOUDY("Partly cloudy"),
    RAINY("rainy"),
    UNKNOWN("");

    private final String text;

    OverallWeather(String text) {
        this.text = text;
    }

    public static OverallWeather getFromString(String text) {
        for (OverallWeather state : OverallWeather.values()) {
            if (state.text.equalsIgnoreCase(text)) {
                return state;
            }
        }
        return UNKNOWN;
    }
}
