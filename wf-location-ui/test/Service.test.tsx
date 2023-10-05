import {getForecast} from "../src/service/Service";

const MOCK_DATA = {
    "country": "Poland",
    "city": "Warsaw",
    "date": "04.10.2023 10:30 AM",
    "weather": {
        "overall": "PARTLY_CLOUDY",
        "temperature": {
            "scale": "CELSIUS",
            "degrees": 14
        }
    }
};

describe('Fetch weather forecast', () => {
    // @ts-ignore
    global.fetch = jest.fn(() => Promise.resolve({
        json: () => Promise.resolve(MOCK_DATA)
    }));

    afterEach(() => {
        jest.restoreAllMocks();
    });

    it('perform a fetch weather forecast', async () => {
        const response = await getForecast("Poland", "Warsaw");

        expect(response.country).toEqual("Poland");
        expect(response.city).toEqual("Warsaw");
        expect(response.date).toEqual("04.10.2023 10:30 AM");
        expect(response.weather.overall).toEqual("PARTLY_CLOUDY");
        expect(response.weather.temperature.scale).toEqual("CELSIUS");
        expect(response.weather.temperature.degrees).toEqual(14);
        expect(fetch).toHaveBeenCalledTimes(1);
    });
});