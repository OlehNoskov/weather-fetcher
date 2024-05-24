import React from "react";

import {Card, Typography} from "@mui/material";
import {Forecast} from "../dto/Forecast";
import {OverallWeather} from "../enum/OverallWeather";

import './WeatherForecastPage.css';

interface IForecast {
    forecast: Forecast
}

export default function WeatherForecastPage(props: IForecast) {

    function getWeatherImage(overallWeather: string) {
        switch (overallWeather) {
            case OverallWeather.SUNNY:
                return <img src={"/images/sunny.png"} alt="Sunny"/>;
            case OverallWeather.PARTLY_CLOUDY:
                return <img src={"/images/partly-cloudy.png"} alt="Partly cloudy"/>;
            case OverallWeather.CLOUDY:
                return <img src={"/images/cloudy.png"} alt="Cloudy"/>;
            case OverallWeather.RAINY:
                return <img src={"/images/rainy.png"} alt="Rainy"/>;
            case OverallWeather.UNKNOWN:
                return <img src={"/images/unknown.png"} alt="Unknown weather"/>;
        }
    }

    return (
        <Card className={"weather-card"}>
            <div className={"left-block-info"} data-testid="title">
                <Typography variant="h6" className={"title"}>
                    CURRENT WEATHER
                    <p className={"location"}>{props.forecast.country} {props.forecast.city}</p>
                </Typography>
                <div className={"image-info"}>
                    {getWeatherImage(props.forecast.overall!)}
                </div>
            </div>
            <div className={"degrees"}>
                {props.forecast.degrees} {'°C'}
                <p className={"overall-weather"}> {props.forecast.overall}</p>
            </div>
        </Card>
    );
};