import React, {useEffect, useState} from "react";
import './LocationPage.css';
import {Alert, Box, Button, Icon, Modal, TextField, Typography} from "@mui/material";
import {Forecast} from "../dto/Forecast";
import {getForecast} from "../service/Service";
import WeatherForecastPage from "./WeatherForecastPage";

export default function LocationPage() {
    const [forecast, setForecast] = useState<Forecast>();
    const [openModal, setOpenModal] = React.useState(false);
    const [country, setCountry] = useState<string>("");
    const [city, setCity] = useState<string>("");

    useEffect(() => {
        window.localStorage.setItem("city", city!);
        window.localStorage.setItem("country", country!);
    }, [country, city]);

    const isDisabledButton = (): boolean => {
        return country?.length <= 2 || city?.length <= 2;
    };

    const getForecastWeather = () => {
        getForecast(country!, city!).then((response) => setForecast(response));
        setOpenModal(false);
    };

    return (
        <div className="App">
            <Typography variant="h6" className={"change-location"}>
                Change location
                <Button variant="contained" onClick={() => setOpenModal(true)}
                        size={"large"} color="success" className={"change-button"}>Change</Button>
            </Typography>
            <Modal
                open={openModal}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description">
                <Box className={"box"}>
                    <Typography variant="h6" className={"title-modal"}>
                        Weather forecast
                        <Button className={"modal-close-button"} onClick={() => setOpenModal(false)}>
                            <Icon>
                                <img className={"close-image"} src="/images/close-icon.svg" alt={'close-icon'}/>
                            </Icon>
                        </Button>
                    </Typography>
                    {isDisabledButton() &&
                        <Alert severity="warning" className={"alert-message"}>
                            Please, input country and city for showing weather forecast!
                        </Alert>}
                    <div className={"inputs"}>
                        <div>
                            <TextField
                                error={isDisabledButton()}
                                label="Country" variant="outlined"
                                type="text" value={country}
                                onChange={(value) => {
                                    setCountry(value.target.value);
                                }} required={true}/>
                        </div>
                        <div>
                            <TextField
                                error={isDisabledButton()}
                                label="City" variant="outlined"
                                className={"city"} type="text" value={city}
                                onChange={(value) => {
                                    setCity(value.target.value);
                                }} required={true}/>
                        </div>
                    </div>
                    <div className={"search-button"}>
                        <Button variant="contained" onClick={getForecastWeather}
                                size={"large"} color="success" disabled={isDisabledButton()}>
                            Search
                        </Button>
                    </div>
                </Box>
            </Modal>
            {forecast !== undefined && <WeatherForecastPage forecast={forecast}/>}
        </div>
    );
};