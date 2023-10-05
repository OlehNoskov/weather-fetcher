import {
    getCitiesByCountryAndIntervalStatistic,
    getCitiesByCountryStatistic,
    getCitiesByIntervalStatistic,
    getCitiesStatistic,
    getCountriesByIntervalStatistic,
    getCountriesStatistic
} from "../src/service/Service";
import {DateInterval} from "../src/enum/DateInterval";
import {Statistic} from "../src/dto/Statistic";

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

describe('Fetch weather forecast', () => {

    const responsePromise = (data: Statistic[]) => {
        // @ts-ignore
        return global.fetch = jest.fn(() => Promise.resolve({
            json: () => Promise.resolve(data)
        }));
    }

    afterEach(() => {
        jest.restoreAllMocks();
    });

    it('perform a fetch countries statistic', async () => {
        responsePromise(COUNTRIES);

        const response = getCountriesStatistic();
        const result = await response.then();

        expect(result[0].data).toEqual("Ukraine");
        expect(result[0].count).toEqual(28);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch countries by interval statistic', async () => {
        responsePromise(COUNTRIES);

        const response = getCountriesByIntervalStatistic(DateInterval.ONE_WEEK);
        const result = await response.then();

        expect(result[0].data).toEqual("Ukraine");
        expect(result[0].count).toEqual(28);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch cities statistic', async () => {
        responsePromise(CITIES);

        const response = getCitiesStatistic();
        const result = await response.then();

        expect(result[0].data).toEqual("Kharkiv");
        expect(result[0].count).toEqual(20);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch cities by interval statistic', async () => {
        responsePromise(CITIES);
        const response = getCitiesByIntervalStatistic(DateInterval.ONE_WEEK);
        const result = await response.then();

        expect(result[0].data).toEqual("Kharkiv");
        expect(result[0].count).toEqual(20);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch cities by country statistic', async () => {
        responsePromise(CITIES);

        const response = getCitiesByCountryStatistic("Ukraine");
        const result = await response.then();

        expect(result[0].data).toEqual("Kharkiv");
        expect(result[0].count).toEqual(20);
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('perform a fetch cities by country and interval statistic', async () => {
        responsePromise(CITIES);

        const response = getCitiesByCountryAndIntervalStatistic("Ukraine", DateInterval.ONE_WEEK);
        const result = await response.then();

        expect(result[0].data).toEqual("Kharkiv");
        expect(result[0].count).toEqual(20);
        expect(fetch).toHaveBeenCalledTimes(1);
    });
});