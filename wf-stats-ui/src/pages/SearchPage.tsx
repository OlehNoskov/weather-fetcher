import React, {useState} from "react";
import {Button, TextField} from "@mui/material";
import {getForecast} from "../service/Service";
import './SearchPage.css';
import {Forecast} from "../dto/Forecast";
import ShowForecast from "./ShowForecast";

export default function SearchPage() {
    const [forecast, setForecast] = useState<Forecast>();
    const [country, setCountry] = useState<string>("");
    const [city, setCity] = useState<string>("");

    const getForecastWeather = () => {
        getForecast(country, city).then((response) => setForecast(response));
    };


    const isDisabledButton = (): boolean => {
        return country?.length === 0 || city?.length === 0;
    };

    return (
        <div className="App">
            <div className={"header"}>
                <h1 className={"main-title"}>World Forecast</h1>
            </div>
            <form action="">
                <div className={"inputs"}>
                    <div>
                        <TextField className={"input"}
                                   label="Country" variant="outlined"
                                   type="text" value={country}
                                   onChange={(value) => {
                                       setCountry(value.target.value);
                                   }} required={true}/>
                    </div>
                    <div>
                        <TextField label="City" variant="outlined"
                                   className={"city"} type="text" value={city}
                                   onChange={(value) => {
                                       setCity(value.target.value);
                                   }} required={true}/>
                    </div>
                    <Button variant="contained" onClick={getForecastWeather}
                            className={"search-button"} disabled={isDisabledButton()}>
                        Search
                    </Button>
                </div>
            </form>
            <hr/>
            <ShowForecast forecast={forecast}/>
        </div>
    );
};