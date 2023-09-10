import {Button} from "@mui/material";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {getCountriesStatistic} from "../service/Service";
import {Statistic} from "../dto/Statistic";

export default function StatisticPage() {
    const [statistics, setStatistics] = useState<Statistic[]>();
    const navigate = useNavigate();

    const navigateDashboard = () => {
        navigate('/weather');
    };

    const getForecastWeather = () => {
        getCountriesStatistic().then((response) => setStatistics(response));
        if (statistics) {
            console.log(statistics);
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