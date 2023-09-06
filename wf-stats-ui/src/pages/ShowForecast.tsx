import React, {useState} from "react";
import {Forecast} from "../dto/Forecast";
import {ScaleSymbols} from "../enum/ScaleSymbols";
import {Scale} from "../enum/Scale";
import './ShowForecast.css';

interface ForecastProps {
    forecast?: Forecast;
}

export default function ShowForecast(props: ForecastProps) {
    const [scaleSymbol, setScaleSymbol] = useState<ScaleSymbols>(ScaleSymbols.CELSIUS);

    const getScaleSymbol = () => {
        return props.forecast?.weather.temperature.scale === Scale.CELSIUS ?
            setScaleSymbol(ScaleSymbols.CELSIUS) : setScaleSymbol(ScaleSymbols.FAHRENHEIT);
    };


    return (
        <div className={"show-forecast"}>
            <div className={"country"}>
                <h1>Country</h1>
                <h1>{props.forecast?.country}</h1>
            </div>
            <div className={"city"}>
                <h1>City</h1>
                <h1>{props.forecast?.city}</h1>
            </div>
            <div className={"overall"}>
                <h1>Overall</h1>
                <h1>{props.forecast?.weather.overall}</h1>
            </div>
            <div className={"degrees"}>
                <h1>Degrees</h1>
                <h1>{props.forecast?.weather.temperature.degrees} {scaleSymbol}</h1>
            </div>
            <hr/>
        </div>
    );
};