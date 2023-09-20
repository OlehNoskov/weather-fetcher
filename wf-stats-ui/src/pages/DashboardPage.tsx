import React, {useEffect, useState} from "react";
import {Alert, TextField} from "@mui/material";
import './DashboardPage.css';
import ModalForecastPage from "./ModalForecastPage";
import StatisticPage from "./StatisticPage";

export default function DashboardPage() {
    const [country, setCountry] = useState(window.localStorage.getItem("country"));
    const [city, setCity] = useState(window.localStorage.getItem("city"));

    const isDisabledButton = (): boolean => {
        // @ts-ignore
        return country?.length <= 2 || city?.length <= 2;
    };

    useEffect(() => {
        window.localStorage.setItem("country", country!);
    }, [country]);

    useEffect(() => {
        window.localStorage.setItem("city", city!);
    }, [city]);

    return (
        <div className="App">
            {isDisabledButton() ?
                <Alert severity="warning" className={"header"}>
                    Please, input country and city for showing weather forecast!
                </Alert>
                :
                <></>
            }
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
                <ModalForecastPage country={country} city={city} isDisabled={isDisabledButton()}/>
            </div>
            <div className={"statistic-page"}>
                <StatisticPage/>
            </div>
        </div>
    );
};