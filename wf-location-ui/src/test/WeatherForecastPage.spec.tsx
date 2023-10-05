import {render} from '@testing-library/react';
import '@testing-library/jest-dom';
import WeatherForecastPage from "../pages/WeatherForecastPage";
import {Forecast} from "../dto/Forecast";
import {OverallWeather} from "../enum/OverallWeather";
import {Scale} from "../enum/Scale";

describe('LocationPage', () => {

    const forecast : Forecast = {
        country: "Poland",
        city: "Warsaw",
        date: new Date(),
        weather: {
            overall: OverallWeather.UNKNOWN,
            temperature: {
                scale: Scale.CELSIUS,
                degrees: 15
            }
        }
    }

    it('should render WeatherForecastPage without opened modal window unknown weather', () => {
        const {asFragment} = render(<WeatherForecastPage forecast={forecast}/>);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render WeatherForecastPage without opened modal window sunny weather', () => {
        forecast.weather.overall = OverallWeather.SUNNY;
        const {asFragment} = render(<WeatherForecastPage forecast={forecast}/>);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render WeatherForecastPage without opened modal window partly cloudy weather', () => {
        forecast.weather.overall = OverallWeather.PARTLY_CLOUDY;
        const {asFragment} = render(<WeatherForecastPage forecast={forecast}/>);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render WeatherForecastPage without opened modal window cloudy weather', () => {
        forecast.weather.overall = OverallWeather.CLOUDY;
        const {asFragment} = render(<WeatherForecastPage forecast={forecast}/>);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render WeatherForecastPage without opened modal window rainy weather', () => {
        forecast.weather.overall = OverallWeather.RAINY;
        const {asFragment} = render(<WeatherForecastPage forecast={forecast}/>);

        expect(asFragment()).toMatchSnapshot();
    });
});
