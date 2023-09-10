import {Forecast} from "../dto/Forecast";
import {Statistic} from "../dto/Statistic";

export async function getForecast(country?: string, city?: string): Promise<Forecast> {
    return await fetch(`/weather/countries/${country}/cities/${city}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCountriesStatistic(): Promise<Statistic[]> {
    return await fetch(`localhost:8081/statistic/countries`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCitiesStatistic(): Promise<Statistic[]> {
    return await fetch(`localhost:8081/statistic/cities`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCitiesByCountryStatistic(country?: string): Promise<Statistic[]> {
    return await fetch(`localhost:8081/statistic/cities/${country}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCountriesBetweenDatesStatistic(startDate?: Date, finishDate?: Date): Promise<Statistic[]> {
    return await fetch(`localhost:8081/statistic/countries/date/between?startDate=${startDate}&finishDate=${finishDate}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCitiesBetweenDatesStatistic(startDate?: Date, finishDate?: Date): Promise<Statistic[]> {
    return await fetch(`localhost:8081/statistic/cities/date/between?startDate=${startDate}&finishDate=${finishDate}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}


export async function getCitiesByCountryAndBetweenDatesStatistic(country: string, startDate?: Date, finishDate?: Date): Promise<Statistic[]> {
    return await fetch(`localhost:8081/statistic/cities/${country}/date/between?startDate=${startDate}&finishDate=${finishDate}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}
