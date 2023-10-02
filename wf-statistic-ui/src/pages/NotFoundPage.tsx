import React from "react";
import {Button} from "@mui/material";
import {useNavigate} from "react-router-dom";

export default function NotFoundPage() {
    const navigate = useNavigate();
    const navigateDashboard = () => {
        navigate('/');
    };

    return (
        <div className={"App"}>
            <div className={"not-found"}>
                <h1>Ooops! Page not found!</h1>
                <Button className={"button-home"} variant="contained"
                        onClick={navigateDashboard} size={"large"} color="error">
                    Dashboard
                </Button>
            </div>
        </div>
    );
};