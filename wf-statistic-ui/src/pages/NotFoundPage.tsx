import React from "react";
import {Button} from "@mui/material";
import {Link} from "react-router-dom";

export default function NotFoundPage() {
    return (
        <div className={"App"}>
            <div className={"not-found"}>
                <h1>Ooops! Page not found!</h1>
                <Button className={"button-home"} variant="contained"
                        component={Link} to="/" size={"large"} color="error">
                    Dashboard
                </Button>
            </div>
        </div>
    );
};