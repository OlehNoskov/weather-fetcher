import {Forecast} from "../dto/Forecast";
import {Statistic} from "../dto/Statistic";

export async function getForecast(country: string, city: string): Promise<Forecast> {
    return await fetch(`/weather/countries/${country}/cities/${city}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCountriesStatistic(interval?: string): Promise<Statistic[]> {
    return await fetch(`/statistic/countries?interval=${interval}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCitiesByCountryStatistic(country?: string, interval?: string,): Promise<Statistic[]> {
    return await fetch(`/statistic/cities?country=${country}&interval=${interval}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCitiesStatistic(interval?: string): Promise<Statistic[]> {
    return await fetch(`/statistic/cities?interval=${interval}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}
