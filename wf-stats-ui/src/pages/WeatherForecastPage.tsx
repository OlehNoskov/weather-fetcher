import React from "react";
import {Card, Typography} from "@mui/material";
import {Forecast} from "../dto/Forecast";
import {OverallWeather} from "../enum/OverallWeather";
import {ScaleSymbols} from "../enum/ScaleSymbols";
import './WeatherForecastPage.css';

interface IForecast {
    forecast: Forecast
}

export default function WeatherForecastPage(props: IForecast) {

    function getWeatherImage(overallWeather: OverallWeather) {
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
            <Typography variant="h6" className={"title"}>
                CURRENT WEATHER
                <p className={"location"}>{props.forecast?.country} {props.forecast?.city}</p>
            </Typography>
            <div className={"info"}>
                {getWeatherImage(props.forecast?.weather.overall!)}
            </div>
            <div className={"degrees"}>
                {props.forecast?.weather.temperature.degrees} {ScaleSymbols.CELSIUS}
                <p className={"overall-weather"}> {props.forecast?.weather.overall}</p>
            </div>
        </Card>
    );
};