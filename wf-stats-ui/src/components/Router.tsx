import React from "react";
import {Route, Routes} from "react-router-dom";
import StatisticPage from "../pages/StatisticPage";
import ErrorPage from "../pages/ErrorPage";
import DashboardPage from "../pages/DashboardPage";

export const Router = () => {
    return (
        <div>
            <Routes>
                <Route path="/dashboard" element={<DashboardPage/>}/>
                <Route path="/statistic" element={<StatisticPage/>}/>
                <Route path='/*' element={<ErrorPage/>}/>
            </Routes>
        </div>
    );
};