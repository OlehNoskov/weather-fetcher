import {render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';

import WeatherForecastPage from "../pages/WeatherForecastPage";
import {Forecast} from "../dto/Forecast";
import {OverallWeather} from "../enum/OverallWeather";

const MOCK_FORECAST : Forecast = {
    country: "Poland",
    city: "Warsaw",
    date: new Date(),
    overall: OverallWeather.UNKNOWN,
    scale: 'Celsius',
    degrees: 15
}

describe('LocationPage', () => {

    it('should render WeatherForecastPage without opened modal window unknown weather', () => {
        const {asFragment} = render(<WeatherForecastPage forecast={MOCK_FORECAST}/>);
        expect(asFragment()).toMatchSnapshot();
    });

    it('should render WeatherForecastPage without opened modal window sunny weather', () => {
        MOCK_FORECAST.overall = OverallWeather.SUNNY;
        const {asFragment} = render(<WeatherForecastPage forecast={MOCK_FORECAST}/>);
        expect(asFragment()).toMatchSnapshot();
    });

    it('should render WeatherForecastPage without opened modal window partly cloudy weather', () => {
        MOCK_FORECAST.overall = OverallWeather.PARTLY_CLOUDY;
        const {asFragment} = render(<WeatherForecastPage forecast={MOCK_FORECAST}/>);
        expect(asFragment()).toMatchSnapshot();
    });

    it('should render WeatherForecastPage without opened modal window cloudy weather', () => {
        MOCK_FORECAST.overall = OverallWeather.CLOUDY;
        const {asFragment} = render(<WeatherForecastPage forecast={MOCK_FORECAST}/>);
        expect(asFragment()).toMatchSnapshot();
    });

    it('should render WeatherForecastPage without opened modal window rainy weather', () => {
        MOCK_FORECAST.overall = OverallWeather.RAINY;
        const {asFragment} = render(<WeatherForecastPage forecast={MOCK_FORECAST}/>);
        expect(asFragment()).toMatchSnapshot();
    });

    it('should render WeatherForecastPage with values', () => {
        MOCK_FORECAST.overall = OverallWeather.RAINY;
        const {asFragment} = render(<WeatherForecastPage forecast={MOCK_FORECAST}/>);

        const location = screen.getByText('Poland Warsaw');
        const degrees = screen.getByText('15 °C');
        const overall = screen.getByText('Rainy');

        expect(location).toBeInTheDocument();
        expect(degrees).toBeInTheDocument();
        expect(overall).toBeInTheDocument();
        expect(asFragment()).toMatchSnapshot();
    });
});
