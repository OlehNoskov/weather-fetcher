import React, {Suspense} from "react";
import './DashboardPage.css';
import StatisticPage from "./StatisticPage";

const Location = React.lazy(() => import('Location/LocationPage'));

export default function DashboardPage() {

    return (
        <div className="App">
            <Suspense fallback={<h1>Loading...</h1>}>
                <Location/>
            </Suspense>
            <StatisticPage/>
        </div>
    );
};