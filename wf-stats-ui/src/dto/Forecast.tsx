import {Weather} from "./Weather";

export interface Forecast {
    country: string,
    city: string,
    date: Date,
    weather: Weather
}
