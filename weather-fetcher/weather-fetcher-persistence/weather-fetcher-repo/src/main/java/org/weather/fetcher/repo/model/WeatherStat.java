package org.weather.fetcher.repo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Entity
@Table(name = "weather_stat")
@NoArgsConstructor
@AllArgsConstructor
public class WeatherStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer resultId;
    private String country;
    private String city;
    @Column(name = "result_date")
    private LocalDate date;
    @Column(name = "weather_overall")
    private String overall;
    @Column(name = "weather_temperature")
    private Float temperature;
    @Column(name = "weather_scale")
    private String scale;

    @Override
    public String toString() {
        return "WeatherResult{" +
                "id='" + resultId + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", resultDate=" + date +
                ", weatherOverall='" + overall + '\'' +
                ", weatherTemperature='" + temperature + '\'' +
                ", weatherScale='" + scale + '\'' +
                '}';
    }
}
