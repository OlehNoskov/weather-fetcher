import {
    getCitiesStatistic,
    getCountriesStatistic
} from "../service/Service";

describe('Fetch weather statistic', () => {

    beforeEach(() => {
        global.fetch = jest.fn(() => Promise.resolve({
            json: () => Promise.reject()
        })) as jest.Mock
    });

    afterEach(() => {
        jest.restoreAllMocks();
    });

    it('should throw error from backend and show error in console log when when try to get countries statistic', async () => {
        await getCountriesStatistic('test');
        expect(fetch).toHaveBeenCalledTimes(1);
    });

    it('should throw error from backend and show error in console log when when try to get cities statistic', async () => {
        await getCitiesStatistic('test', 'test');
        expect(fetch).toHaveBeenCalledTimes(1);
    });
});