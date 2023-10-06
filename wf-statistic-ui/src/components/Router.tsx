import React from "react";
import {Route, Routes} from "react-router-dom";
import NotFoundPage from "../pages/NotFoundPage";
import DashboardPage from "../pages/DashboardPage";
import {DiagramPage} from "../pages/DiagramPage";

export const Router = () => {
    return (
        <Routes>
            <Route path="/" element={<DashboardPage/>}>
                <Route path="countries" element={<DiagramPage/>}/>
                <Route path="cities" element={<DiagramPage/>}/>
                <Route path="cities/country" element={<DiagramPage/>}/>
            </Route>
            <Route path='*' element={<NotFoundPage/>}/>
        </Routes>
    );
};