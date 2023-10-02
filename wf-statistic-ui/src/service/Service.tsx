import {Statistic} from "../dto/Statistic";

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

export async function getCitiesByCountryStatistic(country: string): Promise<Statistic[]> {
    return await fetch(`/statistic/cities/country?country=${country}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCitiesByCountryAndIntervalStatistic(country: string, interval: string): Promise<Statistic[]> {
    return await fetch(`/statistic/cities/country/interval?country=${country}&interval=${interval}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}