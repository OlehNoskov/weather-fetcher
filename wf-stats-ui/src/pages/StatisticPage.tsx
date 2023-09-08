import {Button, TextField} from "@mui/material";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {Forecast} from "../dto/Forecast";
import {getAllForecast} from "../service/Service";

export default function StatisticPage() {
    const [forecasts, setForecasts] = useState<Forecast[]>();
    const navigate = useNavigate();

    const navigateDashboard = () => {
        navigate('/dashboard');
    };

    const getForecastWeather = () => {
        getAllForecast().then((response) => setForecasts(response));
        if (forecasts) {
            console.log(forecasts[0].country);
        }
    };

    return (
        <div>
            <h1>Statistic Page</h1>
            <Button variant="contained" onClick={navigateDashboard}>Dashboard</Button>
            <Button variant="contained" onClick={getForecastWeather}>Test</Button>
        </div>
    );
};