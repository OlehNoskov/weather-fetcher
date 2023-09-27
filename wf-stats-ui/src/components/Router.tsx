import React, {Suspense} from "react";
import {Route, Routes} from "react-router-dom";
import NotFoundPage from "../pages/NotFoundPage";
import DashboardPage from "../pages/DashboardPage";
import {DiagramPage} from "../pages/DiagramPage";

const Location = React.lazy(() => import("Location/Test"));

export const Router = () => {
    return (
        <>
            <Routes>
                <Route path="/" element={<DashboardPage/>}>
                    <Route path="countries" element={<DiagramPage/>}/>
                    <Route path="cities" element={<DiagramPage/>}/>
                    <Route path="cities/country" element={<DiagramPage/>}/>
                </Route>
                <Route path="/location" element={
                    <Suspense fallback={<div>Loading remote page!</div>}>
                        <Location/>
                    </Suspense>}/>
                <Route path='/*' element={<NotFoundPage/>}/>
            </Routes>
        </>
    );
};