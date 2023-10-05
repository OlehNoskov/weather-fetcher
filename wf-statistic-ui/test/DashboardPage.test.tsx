import {render, waitFor} from '@testing-library/react';
import '@testing-library/jest-dom';
import {MemoryRouter} from "react-router-dom";
import DashboardPage from "../src/pages/DashboardPage";

describe('DashboardPage', () => {
    it('should render DashboardPage without open modal and statistic diagram', async () => {
        const {asFragment} = render(
            <MemoryRouter>
                <DashboardPage/>
            </MemoryRouter>);

        await waitFor(() => {
            expect(asFragment()).toMatchSnapshot();
        });
    });
});