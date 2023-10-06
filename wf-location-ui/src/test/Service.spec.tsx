import {
    getForecast,
} from "../service/Service";


describe('Fetch weather forecast', () => {

    beforeEach(() => {
        global.fetch = jest.fn(() => Promise.resolve({
            json: () => Promise.reject()
        })) as jest.Mock
    });

    afterEach(() => {
        jest.restoreAllMocks();
    });

    it('should throw error from backend and show error in console log when try to get weather forecast', async () => {
        await getForecast('test', 'test');
        expect(fetch).toHaveBeenCalledTimes(1);
    });
});