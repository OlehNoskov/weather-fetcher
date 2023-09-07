import {Button, TextField} from "@mui/material";
import React from "react";
import {useNavigate} from "react-router-dom";

export default function StatisticPage() {
    const navigate = useNavigate();
    const navigateDashboard = () => {
        navigate('/dashboard');
    };

    return (
        <div>
            <h1>Statistic Page</h1>
            <Button variant="contained" onClick={navigateDashboard}>Dashboard</Button>
        </div>
    );
};