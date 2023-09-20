import React from "react";
import {Route, Routes} from "react-router-dom";
import ErrorPage from "../pages/ErrorPage";
import DashboardPage from "../pages/DashboardPage";

export const Router = () => {
    return (
        <div>
            <Routes>
                <Route path="/" element={<DashboardPage/>}/>
                <Route path='/*' element={<ErrorPage/>}/>
            </Routes>
        </div>
    );
};