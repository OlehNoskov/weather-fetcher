import {screen, render} from '@testing-library/react';
import '@testing-library/jest-dom';
import LocationPage from '../src/pages/LocationPage';
import userEvent from '@testing-library/user-event';

describe('LocationPage', () => {
    const user = userEvent.setup();
    const mockClick = jest.fn();

    global.fetch = mockClick(() => Promise.resolve({
        json: () => Promise.resolve()
    }));

    it('should render LocationPage without opened modal window', () => {
        const {asFragment} = render(<LocationPage/>);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render LocationPage after click on the button Change', () => {
        const {asFragment} = render(<LocationPage/>);
        const changeButton = screen.getByText('Change');
        user.click(changeButton);

        expect(mockClick).toHaveBeenCalledTimes(1);
        expect(asFragment()).toMatchSnapshot();
    });


    it('should render modal with inputs and disabled button after typing incorrect text', async () => {
        const {asFragment} = render(<LocationPage/>);
        const changeButton = screen.getByText('Change');
        await user.click(changeButton);

        const inputCountry = screen.getByText('Country');
        inputCountry.style.pointerEvents = 'auto';
        expect(inputCountry).not.toHaveFocus();
        await user.type(inputCountry, 'qq');

        const inputCity = screen.getByText('City');
        inputCity.style.pointerEvents = 'auto';
        expect(inputCity).not.toHaveFocus();
        await user.type(inputCity, 'qq');

        const searchButton = screen.getByText('Search');
        searchButton.style.pointerEvents = 'auto';
        await user.click(searchButton);

        expect(searchButton).toHaveProperty('disabled', true);
        expect(asFragment()).toMatchSnapshot();
    });

    it('should render modal with inputs and active button after typing correct text and show weather forecast', async () => {
        // @ts-ignore
        global.fetch = jest.fn(() => Promise.resolve({
            json: () => Promise.resolve()
        }));

        const {asFragment} = render(<LocationPage/>);
        const changeButton = screen.getByText('Change');
        await user.click(changeButton);

        const inputCountry = screen.getByText('Country');
        inputCountry.style.pointerEvents = 'auto';
        expect(inputCountry).not.toHaveFocus();
        await user.type(inputCountry, "Ukraine");

        const inputCity = screen.getByText('City');
        inputCity.style.pointerEvents = 'auto';
        expect(inputCity).not.toHaveFocus();
        await user.type(inputCity, 'Kharkiv');

        const searchButton = screen.getByText('Search');
        searchButton.style.pointerEvents = 'auto';
        await user.click(searchButton);

        expect(searchButton).toHaveProperty('disabled', false);
        expect(asFragment()).toMatchSnapshot();
    });
});
