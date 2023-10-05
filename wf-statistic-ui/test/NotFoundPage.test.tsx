import {screen, render} from '@testing-library/react';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import NotFoundPage from "../src/pages/NotFoundPage";
import {MemoryRouter} from "react-router-dom";

describe('NotFoundPage', () => {
    it('should render NotFoundPage when type incorrect url', () => {
        const {asFragment} = render(
            <MemoryRouter>
                <NotFoundPage/>
            </MemoryRouter>);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should navigate to DashboardPage after click on \'Dashboard\' button', () => {
        render(
            <MemoryRouter>
                <NotFoundPage/>
            </MemoryRouter>);

        const dashboardButton = screen.getByText('Dashboard');
        userEvent.click(dashboardButton);

        expect(window.location.pathname).toBe('/');
    });
});
