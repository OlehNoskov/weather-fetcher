import {screen, render} from '@testing-library/react';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import NotFoundPage from "../pages/NotFoundPage";
import {MemoryRouter} from "react-router-dom";

describe('NotFoundPage', () => {
    const renderNotFoundPage = () => render(
        <MemoryRouter>
            <NotFoundPage/>
        </MemoryRouter>);

    it('should render NotFoundPage when type incorrect url', () => {
        const {asFragment} = render(
            <MemoryRouter>
                <NotFoundPage/>
            </MemoryRouter>);

        expect(asFragment()).toMatchSnapshot();
    });

    it('should navigate to DashboardPage after click on \'Dashboard\' button', () => {
        renderNotFoundPage();

        const dashboardButton = screen.getByText('Dashboard');
        userEvent.click(dashboardButton);
        expect(window.location.pathname).toBe('/');
    });

    it('should do not navigate to \'countries\' page after click on \'Dashboard\' button', () => {
        renderNotFoundPage();

        const dashboardButton = screen.getByText('Dashboard');
        userEvent.click(dashboardButton);
        expect(window.location.pathname).not.toBe('/countries');
    });
});
