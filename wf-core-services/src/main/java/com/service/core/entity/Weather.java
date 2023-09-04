package com.service.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.core.util.enums.OverallWeather;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private OverallWeather overall;
    @OneToOne(cascade = CascadeType.ALL)
    private Temperature temperature;
}
