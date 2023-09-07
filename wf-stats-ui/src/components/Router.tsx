import React from "react";
import {Route, Routes} from "react-router-dom";
import SearchPage from "../pages/SearchPage";
import StatisticPage from "../pages/StatisticPage";
import ErrorPage from "../pages/ErrorPage";

export const Router = () => {
    return (
        <div>
            <Routes>
                <Route path="/search"
                       element={<SearchPage/>}/>
                <Route path="/statistic"
                       element={<StatisticPage/>}/>
                <Route path='*' element={<ErrorPage/>}/>
            </Routes>
        </div>
    );
};