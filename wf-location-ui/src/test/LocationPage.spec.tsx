import {screen, render} from '@testing-library/react';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';

import LocationPage from '../pages/LocationPage';
import {Forecast} from "../dto/Forecast";
import {OverallWeather} from "../enum/OverallWeather";

const MOCK_FORECAST: Forecast = {
    country: "Poland",
    city: "Warsaw",
    date: new Date(),
    overall: OverallWeather.UNKNOWN,
    scale: 'Celsius',
    degrees: 15
}

describe('LocationPage', () => {
    const user = userEvent.setup();

    beforeEach(() => {
        global.fetch = jest.fn(() => Promise.resolve({
            json: () => Promise.resolve(MOCK_FORECAST)
        })) as jest.Mock
    });

    it('should render LocationPage without opened modal window', () => {
        const {asFragment} = render(<LocationPage/>);
        expect(asFragment()).toMatchSnapshot();
    });

    it('should render LocationPage after click on the button \'Change\' ' +
        'and after click on close modal button and show dashboard page', async () => {
        const {asFragment} = render(<LocationPage/>);

        const changeButton = screen.getByText('Change');
        await user.click(changeButton);

        expect(asFragment()).toMatchSnapshot();

        const closeModal = screen.getByTestId('close-modal');
        await user.click(closeModal);
    });


    it('should render modal with inputs and disabled button after typing incorrect text', async () => {
        render(<LocationPage/>);

        const changeButton = screen.getByText('Change');
        await user.click(changeButton);

        const inputCountry = screen.getByText('Country');
        const inputCity = screen.getByText('City');
        const searchButton = screen.getByText('Search');

        inputCountry.style.pointerEvents = 'auto';
        inputCity.style.pointerEvents = 'auto';
        searchButton.style.pointerEvents = 'auto';

        await user.type(inputCountry, 'qq');
        await user.type(inputCity, 'qq');
        await user.click(searchButton);

        expect(searchButton).toHaveProperty('disabled', true)

        await user.type(inputCountry, "Ukraine");
        await user.type(inputCity, 'Kharkiv');
        await user.click(searchButton);

        expect(searchButton).toHaveProperty('disabled', false);
        expect(fetch).toHaveBeenCalledTimes(1);
    });
});
