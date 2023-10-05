import {render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';
import {MemoryRouter} from "react-router-dom";
import StatisticPage from "../pages/StatisticPage";
import userEvent from "@testing-library/user-event";

const MOCK_DATA = [
    {
        "data": "test",
        "count": 1
    }
];

describe('StatisticPage', () => {
    const user = userEvent.setup();

    const renderStatisticPage = () => {
        render(
            <MemoryRouter>
                <StatisticPage/>
            </MemoryRouter>);
    };


    beforeEach(() => {
        global.fetch = jest.fn(() => Promise.resolve({
            json: () => Promise.resolve(MOCK_DATA)
        })) as jest.Mock
    });

    it('should render StatisticPage without open statistic diagram.', () => {
        const {asFragment} = render(
            <MemoryRouter>
                <StatisticPage/>
            </MemoryRouter>);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render StatisticPage and show statistic diagram after click \'Countries\' button.', async () => {
        const {asFragment} = render(
            <MemoryRouter>
                <StatisticPage/>
            </MemoryRouter>);

        const countriesButton = screen.getByText('Countries');
        await user.click(countriesButton);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render StatisticPage after click on \'Countries\', \'Cities\' and \'Cities in country\' ' +
        'and filter buttons.', async () => {
        renderStatisticPage();

        const countriesButton = screen.getByText('Countries');
        const citiesButton = screen.getByText('Cities');
        const citiesInCountryButton = screen.getByText('Cities in country');

        const oneWeekButton = screen.getByText('1 week');
        const twoWeekButton = screen.getByText('2 weeks');
        const oneMonthButton = screen.getByText('1 month');
        const resetFilter = screen.getByText('Reset filter');

        await user.click(countriesButton);
        await user.click(oneWeekButton);

        expect(oneWeekButton).toHaveProperty('disabled', true);
        expect(twoWeekButton).toHaveProperty('disabled', false);
        expect(oneMonthButton).toHaveProperty('disabled', false);
        expect(resetFilter).toHaveProperty('disabled', false);

        await user.click(citiesButton);
        await user.click(twoWeekButton);

        expect(oneWeekButton).toHaveProperty('disabled', false);
        expect(twoWeekButton).toHaveProperty('disabled', true);
        expect(oneMonthButton).toHaveProperty('disabled', false);
        expect(resetFilter).toHaveProperty('disabled', false);

        await user.click(oneMonthButton);

        expect(oneWeekButton).toHaveProperty('disabled', false);
        expect(twoWeekButton).toHaveProperty('disabled', false);
        expect(oneMonthButton).toHaveProperty('disabled', true);
        expect(resetFilter).toHaveProperty('disabled', false);

        await user.click(citiesInCountryButton);
        const alertMessage = screen.getByTestId('message-empty-statistic');
        expect(alertMessage).toBeInTheDocument();
    });

    it('should render StatisticPage after click on \'Cities in country\' button and \'Search\' ' +
        'button changes state by input values.', async () => {
        renderStatisticPage();

        const citiesInCountryButton = screen.getByText('Cities in country');
        const oneWeekButton = screen.getByText('1 week');

        await user.click(citiesInCountryButton);
        const searchButton = screen.getByText('Search');
        const inputCountry = screen.getByText('Country');
        inputCountry.style.pointerEvents = 'auto';

        await user.type(inputCountry, 'qq');

        expect(searchButton).toHaveProperty('disabled', true);
        expect(searchButton).toBeInTheDocument();

        await user.type(inputCountry, 'Ukraine');
        expect(searchButton).toHaveProperty('disabled', false);
        await user.click(searchButton);
        await user.click(oneWeekButton);
    });
});