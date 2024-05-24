package org.weather.fetcher.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationResponse {
    @JsonProperty("name")
    private String city;
    private String region;
    private String country;
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lon")
    private String longitude;
    @JsonProperty("tz_id")
    private String timeZone;
    @JsonProperty("localtime_epoch")
    private String localtimeMillis;
    private String localtime;
}
