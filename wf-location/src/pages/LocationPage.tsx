import React, {useEffect, useState} from "react";
import './LocationPage.css';
import {Alert, Box, Button, Icon, Modal, TextField, Typography} from "@mui/material";
import {Forecast} from "../dto/Forecast";
import {getForecast} from "../service/Service";
// import WeatherForecastPage from "./WeatherForecastPage";

export default function LocationPage() {
    // const [forecast, setForecast] = useState<Forecast>();
    // const [openModal, setOpenModal] = React.useState(false);
    // const [country, setCountry] = useState(window.localStorage.getItem("country"));
    // const [city, setCity] = useState(window.localStorage.getItem("city"));
    //
    // const isDisabledButton = (): boolean => {
    //     // @ts-ignore
    //     return country?.length <= 2 || city?.length <= 2;
    // };
    //
    // const isValidCountry = (): boolean => {
    //     // @ts-ignore
    //     return country?.length <= 2;
    // };
    //
    // const isValidCity = (): boolean => {
    //     // @ts-ignore
    //     return city?.length <= 2;
    // };
    //
    // useEffect(() => {
    //     window.localStorage.setItem("country", country!);
    // }, [country]);
    //
    // useEffect(() => {
    //     window.localStorage.setItem("city", city!);
    // }, [city]);
    //
    // const handleOpenModal = () => setOpenModal(true);
    //
    // const handleCloseModal = () => setOpenModal(false);
    //
    // const getForecastWeather = () => {
    //     getForecast(country!, city!).then((response) => setForecast(response));
    //     setOpenModal(false);
    // };
    //
    // function getAlertMessage() {
    //     return isDisabledButton() ?
    //         <Alert severity="warning" className={"alert-message"}>
    //             Please, input country and city for showing weather forecast!
    //         </Alert>
    //         :
    //         <></>
    // }
    //
    // function getWeatherForecast() {
    //     return forecast !== undefined ? <WeatherForecastPage forecast={forecast}/> : <></>
    // }

    return (
        // <div className="App">
        //     <Typography variant="h6" className={"change-location"}>
        //         Change location
        //         <Button variant="contained" onClick={handleOpenModal}
        //                 size={"large"} color="success" className={"change-button"}>Change</Button>
        //     </Typography>
        //     <Modal
        //         open={openModal}
        //         aria-labelledby="modal-modal-title"
        //         aria-describedby="modal-modal-description">
        //         <Box className={"box"}>
        //             <Typography variant="h6" className={"title-modal"}>
        //                 Weather forecast
        //                 <Button className={"modal-close-button"} onClick={handleCloseModal}>
        //                     <Icon>
        //                         <img className={"close-image"} src="/images/close-icon.svg" alt={'close-icon'}/>
        //                     </Icon>
        //                 </Button>
        //             </Typography>
        //             {getAlertMessage()}
        //             <div className={"inputs"}>
        //                 <div>
        //                     <TextField
        //                         error={isValidCountry()}
        //                         label="Country" variant="outlined"
        //                         type="text" value={country}
        //                         onChange={(value) => {
        //                             setCountry(value.target.value);
        //                         }} required={true}/>
        //                 </div>
        //                 <div>
        //                     <TextField
        //                         error={isValidCity()}
        //                         label="City" variant="outlined"
        //                         className={"city"} type="text" value={city}
        //                         onChange={(value) => {
        //                             setCity(value.target.value);
        //                         }} required={true}/>
        //                 </div>
        //             </div>
        //             <div className={"search-button"}>
        //                 <Button variant="contained" onClick={getForecastWeather}
        //                         size={"large"} color="success" disabled={isDisabledButton()}>
        //                     Search
        //                 </Button>
        //             </div>
        //         </Box>
        //     </Modal>
        //     {getWeatherForecast()}
        // </div>
        null
    );
};