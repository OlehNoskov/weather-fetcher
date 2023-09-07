import React, {useEffect, useState} from "react";
import {Button, TextField} from "@mui/material";
import {getForecast} from "../service/Service";
import './SearchPage.css';
import {Forecast} from "../dto/Forecast";
import ShowForecastPage from "./ShowForecastPage";
import {useNavigate} from "react-router-dom";

export default function SearchPage() {
    const [forecast, setForecast] = useState<Forecast>();
    const [country, setCountry] = useState(window.localStorage.getItem("country"));
    const [city, setCity] = useState(window.localStorage.getItem("city"));
    const navigate = useNavigate();

    const navigateToStatistic = () => {
        navigate('/statistic');
    };

    const getForecastWeather = () => {
        getForecast(country!, city!).then((response) => setForecast(response));
    };

    const isDisabledButton = (): boolean => {
        return country?.length === 0 || city?.length === 0;
    };

    useEffect(() => {
        window.localStorage.setItem("country", country!);
    }, [country]);

    useEffect(() => {
        window.localStorage.setItem("city", city!);
    }, [city]);

    return (
        <div className="App">
            <div className={"header"}>
                <h1 className={"main-title"}>Weather forecast</h1>
            </div>
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
            <ShowForecastPage forecast={forecast}/>
            <div className={"statistic-page"}>
                <Button variant="contained" color={"success"} size={"large"} onClick={navigateToStatistic}>
                    Statistic page
                </Button>
            </div>
        </div>
    );
};