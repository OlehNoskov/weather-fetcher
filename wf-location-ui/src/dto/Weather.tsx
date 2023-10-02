import {Temperature} from "./Temperature";
import {OverallWeather} from "../enum/OverallWeather";

export interface Weather {
    overall: OverallWeather,
    temperature: Temperature
}