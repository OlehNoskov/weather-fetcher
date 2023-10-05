import {
    getCitiesByCountryAndIntervalStatistic,
    getCitiesByCountryStatistic,
    getCitiesByIntervalStatistic,
    getCitiesStatistic,
    getCountriesByIntervalStatistic,
    getCountriesStatistic
} from "../service/Service";
import {DateInterval} from "../enum/DateInterval";
import {Statistic} from "../dto/Statistic";

const COUNTRIES = [
    {
        "data": "Ukraine",
        "count": 28
    }
];

const CITIES = [
    {
        "data": "Kharkiv",
        "count": 20
    }
];

global.fetch = jest.fn(() => Promise.resolve({
    json: () => Promise.resolve()
})) as jest.Mock;


describe('Fetch weather forecast', () => {

    it('perform a fetch countries statistic', async () => {
        // responsePromise(COUNTRIES);

        const response = getCountriesStatistic();
        const result = await response.then();

        expect(result[0].data).toEqual("Ukraine");
        expect(result[0].count).toEqual(28);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch countries by interval statistic', async () => {
        // responsePromise(COUNTRIES);

        const response = getCountriesByIntervalStatistic(DateInterval.ONE_WEEK);
        const result = await response.then();

        expect(result[0].data).toEqual("Ukraine");
        expect(result[0].count).toEqual(28);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch cities statistic', async () => {
        // responsePromise(CITIES);

        const response = getCitiesStatistic();
        const result = await response.then();

        expect(result[0].data).toEqual("Kharkiv");
        expect(result[0].count).toEqual(20);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch cities by interval statistic', async () => {
        // responsePromise(CITIES);
        const response = getCitiesByIntervalStatistic(DateInterval.ONE_WEEK);
        const result = await response.then();

        expect(result[0].data).toEqual("Kharkiv");
        expect(result[0].count).toEqual(20);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch cities by country statistic', async () => {
        // responsePromise(CITIES);

        const response = getCitiesByCountryStatistic("Ukraine");
        const result = await response.then();

        expect(result[0].data).toEqual("Kharkiv");
        expect(result[0].count).toEqual(20);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch cities by country and interval statistic', async () => {
        // responsePromise(CITIES);

        const response = getCitiesByCountryAndIntervalStatistic("Ukraine", DateInterval.ONE_WEEK);
        const result = await response.then();

        expect(result[0].data).toEqual("Kharkiv");
        expect(result[0].count).toEqual(20);
        expect(fetch).toHaveBeenCalledTimes(1);
    });
});