import React from "react";
import {Button} from "@mui/material";
import {useNavigate} from "react-router-dom";

export default function ErrorPage() {
    const navigate = useNavigate();
    const navigateDashboard = () => {
        navigate('/dashboard');
    };

    return (
        <div className={"App"}>
            <div className={"not-found"}>
                <h1>Ooops! Page not found!</h1>
                <Button variant="contained" onClick={navigateDashboard}>Dashboard</Button>
            </div>
        </div>
    );
};