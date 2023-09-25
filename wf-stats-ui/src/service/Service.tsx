import {Forecast} from "../dto/Forecast";
import {Statistic} from "../dto/Statistic";

export async function getForecast(country: string, city: string): Promise<Forecast> {
    return await fetch(`/weather/countries/${country}/cities/${city}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCountriesStatistic(): Promise<Statistic[]> {
    return await fetch(`/statistic/countries`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCountriesByIntervalStatistic(interval: string): Promise<Statistic[]> {
    return await fetch(`/statistic/countries/interval?interval=${interval}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCitiesStatistic(): Promise<Statistic[]> {
    return await fetch(`/statistic/cities`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCitiesByIntervalStatistic(interval: string): Promise<Statistic[]> {
    return await fetch(`/statistic/cities/interval?interval=${interval}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}
