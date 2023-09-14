import React, {useEffect, useState} from "react";
import {Alert, Box, Button, Icon, Modal, TextField, Typography} from "@mui/material";
import {getForecast} from "../service/Service";
import './DashboardPage.css';
import {Forecast} from "../dto/Forecast";
import StatisticPage from "./StatisticPage";
import ModalForecast from "../components/ModalForecast";

export default function DashboardPage() {
    const [forecast, setForecast] = useState<Forecast>();
    const [country, setCountry] = useState(window.localStorage.getItem("country"));
    const [city, setCity] = useState(window.localStorage.getItem("city"));
    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);

    const getForecastWeather = () => {
        getForecast(country!, city!).then((response) => setForecast(response));
        handleOpen();
    };

    const isDisabledButton = (): boolean => {
        return country?.length === 0 || city?.length === 0;
    };

    useEffect(() => {
        window.localStorage.setItem("country", country!);
    }, [country]);

    useEffect(() => {
        window.localStorage.setItem("city", city!);
    }, [city]);

    return (
        <div className="App">
            {isDisabledButton() ?
                <Alert severity="info" className={"header"}>
                    Please, input country and city for showing weather forecast!
                </Alert>
                :
                <></>
            }
            <div className={"inputs"}>
                <div>
                    <TextField className={"input"}
                               label="Country" variant="outlined"
                               type="text" value={country}
                               onChange={(value) => {
                                   setCountry(value.target.value);
                               }} required={true}/>
                </div>
                <div>
                    <TextField label="City" variant="outlined"
                               className={"city"} type="text" value={city}
                               onChange={(value) => {
                                   setCity(value.target.value);
                               }} required={true}/>
                </div>
                <Button variant="contained" onClick={getForecastWeather}
                        className={"search-button"} disabled={isDisabledButton()}>
                    Search
                </Button>
                {/*<Modal*/}
                {/*    open={open}*/}
                {/*    onClose={handleClose}*/}
                {/*    aria-labelledby="modal-modal-title"*/}
                {/*    aria-describedby="modal-modal-description">*/}
                {/*    <Box className={"box"}>*/}
                {/*        <Typography id="modal-modal-title" variant="h6" component="h2">*/}
                {/*            <Button className="modal-close-button" onClick={changeOpenState}>*/}
                {/*                <Icon>*/}
                {/*                    <img className="close-image" src="/images/close-icon.svg" alt={'close-icon'}/>*/}
                {/*                </Icon>*/}
                {/*            </Button>*/}
                {/*            Forecast*/}
                {/*        </Typography>*/}
                {/*        <Typography id="modal-modal-description" sx={{mt: 2}}>*/}

                {/*        </Typography>*/}
                {/*    </Box>*/}
                {/*</Modal>*/}
                <ModalForecast open={open}/>
            </div>
            <div className={"statistic-page"}>
                <StatisticPage/>
            </div>
        </div>
    );
};