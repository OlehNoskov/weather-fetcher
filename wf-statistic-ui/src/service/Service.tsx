import {Statistic} from "../dto/Statistic";

export async function getCountriesStatistic(interval?: string): Promise<Statistic[]> {
    return await fetch(`/statistic/countries?interval=${interval}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}

export async function getCitiesStatistic(country?: string, interval?: string): Promise<Statistic[]> {
    return await fetch(`/statistic/cities?country=${country}&interval=${interval}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}
