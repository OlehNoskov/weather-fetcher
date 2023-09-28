import {Forecast} from "../dto/Forecast";

export async function getForecast(country: string, city: string): Promise<Forecast> {
    return await fetch(`/weather/countries/${country}/cities/${city}`)
        .then(response => response.json())
        .catch(rejected => {
            console.log(rejected);
        });
}
