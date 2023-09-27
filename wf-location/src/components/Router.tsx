import React from "react";
import {Route, Routes} from "react-router-dom";
import LocationPage from "../pages/LocationPage";
export const Router = () => {
    return (
        <Routes>
            <Route path="/" element={<LocationPage/>}/>
        </Routes>
    );
};