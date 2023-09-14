import React, {useState} from "react";
import {Box, Button, Icon, Modal, Typography} from "@mui/material";
import {getForecast} from "../service/Service";
import {Forecast} from "../dto/Forecast";
import {OverallWeather} from "../enum/OverallWeather";
import {ScaleSymbols} from "../enum/ScaleSymbols";

interface IModalWeather {
    country: string | null,
    city: string | null,
    isDisabled: boolean
}

export default function ModalForecast(props: IModalWeather) {
    const [forecast, setForecast] = useState<Forecast>();
    const [open, setOpen] = React.useState(false);
    const handleCloseModal = () => setOpen(false);

    const getForecastWeather = () => {
        getForecast(props.country!, props.city!).then((response) => setForecast(response));
        setOpen(true);
    };

    function getWeatherImage(overallWeather: OverallWeather) {
        switch (overallWeather) {
            case OverallWeather.SUNNY:
                return <img src={"/images/sunny.png"} alt="Sunny"/>;
            case OverallWeather.PARTLY_CLOUDY:
                return <img src={"/images/partly-cloudy.png"} alt="Partly cloudy"/>;
            case OverallWeather.CLOUDY:
                return <img src={"/images/cloudy.png"} alt="Cloudy"/>;
            case OverallWeather.RAINY:
                return <img src={"/images/rainy.png"} alt="Rainy"/>;
            case OverallWeather.UNKNOWN:
                return <img src={"/images/unknown.png"} alt="Unknown weather"/>;
        }
    }

    return (
        <div>
            <Button variant="contained" onClick={getForecastWeather}
                    className={"search-button"} disabled={props.isDisabled}>
                Search
            </Button>
            <Modal
                open={open}
                onClose={handleCloseModal}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description">
                <Box className={"box"}>
                    <Typography id="modal-modal-title" variant="h6"
                                component="h2" className={"modal-title"}>
                        <Button className="modal-close-button" onClick={handleCloseModal}>
                            <Icon>
                                <img className="close-image" src="/images/close-icon.svg" alt={'close-icon'}/>
                            </Icon>
                        </Button>
                        CURRENT WEATHER
                        <p className={"location"}>{forecast?.country} {forecast?.city}</p>
                    </Typography>
                    <Typography id="modal-modal-description">
                        <div className={"weather-info"}>
                            <div className={"overall-image"}>
                                {getWeatherImage(forecast?.weather.overall!)}
                            </div>
                            <div className={"degrees"}>
                                {forecast?.weather.temperature.degrees} {ScaleSymbols.CELSIUS}
                            </div>
                            <p> {forecast?.weather.overall}</p>
                        </div>
                    </Typography>
                </Box>
            </Modal>
        </div>
    );
};