import {render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';
import {MemoryRouter} from "react-router-dom";
import StatisticPage from "../src/pages/StatisticPage";
import userEvent from "@testing-library/user-event";

const MOCK_DATA = [
    {
        "data": "test",
        "count": 1
    }
];

describe('StatisticPage', () => {
    const user = userEvent.setup();

    beforeEach(() => {
        // @ts-ignore
        global.fetch = jest.fn(() => Promise.resolve({
            json: () => Promise.resolve(MOCK_DATA)
        }));

    });

    afterEach(() => {
        jest.restoreAllMocks();
    });

    it('should render StatisticPage without open statistic diagram', () => {
        const {asFragment} = render(
            <MemoryRouter>
                <StatisticPage/>
            </MemoryRouter>);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render StatisticPage after click on \'Countries\' button', async () => {
        const {asFragment} = render(
            <MemoryRouter>
                <StatisticPage/>
            </MemoryRouter>);

        const countriesButton = screen.getByText('Countries');
        await user.click(countriesButton);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render StatisticPage after click on \'Cities\' button', async () => {
        const {asFragment} = render(
            <MemoryRouter>
                <StatisticPage/>
            </MemoryRouter>);

        const citiesButton = screen.getByText('Cities');
        await user.click(citiesButton);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should render StatisticPage after click on \'Cities in country\' button and \'Search\' button is disabled', async () => {
        const {asFragment} = render(
            <MemoryRouter>
                <StatisticPage/>
            </MemoryRouter>);

        const citiesInCountryButton = screen.getByText('Cities in country');
        await user.click(citiesInCountryButton);
        const searchButton = screen.getByText('Search');

        const inputCountry = screen.getByText('Country');
        inputCountry.style.pointerEvents = 'auto';
        expect(inputCountry).not.toHaveFocus();
        await user.type(inputCountry, 'qq');

        expect(searchButton).toHaveProperty('disabled', true);
        expect(searchButton).toBeInTheDocument();
        expect(asFragment()).toMatchSnapshot();
    });

    it('should render StatisticPage after click on \'Cities in country\' button and \'Search\' button is active', async () => {
        const {asFragment} = render(
            <MemoryRouter>
                <StatisticPage/>
            </MemoryRouter>);

        const citiesInCountryButton = screen.getByText('Cities in country');
        await user.click(citiesInCountryButton);
        const searchButton = screen.getByText('Search');

        const inputCountry = screen.getByText('Country');
        inputCountry.style.pointerEvents = 'auto';
        expect(inputCountry).not.toHaveFocus();
        await user.type(inputCountry, "Ukraine");

        expect(searchButton).toHaveProperty('disabled', false);
        expect(searchButton).toBeInTheDocument();
        expect(asFragment()).toMatchSnapshot();
    });
});