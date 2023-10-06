import {Alert, Button, ButtonGroup, TextField, Typography,} from "@mui/material";
import {getCitiesStatistic, getCountriesStatistic} from "../service/Service";
import {Statistic} from "../dto/Statistic";
import React, {useEffect, useState} from 'react';
import {DateInterval} from "../enum/DateInterval";
import {TypeStatistic} from "../enum/TypeStatistic";
import {Link, Outlet} from "react-router-dom";

export default function StatisticPage() {
    const [statistics, setStatistics] = useState<Statistic[]>([]);
    const [dateInterval, setDateInterval] = useState<DateInterval>(DateInterval.DEFAULT);
    const [labelDiagram, setLabelDiagram] = useState<TypeStatistic>(TypeStatistic.DEFAULT);
    const [country, setCountry] = useState<string>("");
    const [openDiagram, setOpenDiagram] = useState<boolean>(false);
    const [openButtonSearch, setOpenButtonSearch] = useState<boolean>(false);

    useEffect(() => {
        window.localStorage.setItem("country", country!);
    }, [country]);

    useEffect(() => {
        switch (dateInterval) {
            case DateInterval.ONE_WEEK:
                oneWeekStatistic();
                break;
            case DateInterval.TWO_WEEK:
                twoWeeksStatistic();
                break;
            case DateInterval.ONE_MONTH:
                oneMonthStatistic();
                break;
            case DateInterval.DEFAULT:
                resetDateInterval();
                break;
        }
    }, [dateInterval]);

    const countriesStatistic = () => {
        setLabelDiagram(TypeStatistic.COUNTRIES);
        getCountriesStatistic(getParameter(dateInterval)).then((response) => setStatistics(response));
        showDiagramAndHideCountryInput();
    };

    const citiesStatistic = (typeStatistic: TypeStatistic) => {
        setLabelDiagram(typeStatistic);
        getCitiesStatistic(getParameter(country), getParameter(dateInterval)).then((response) => setStatistics(response));
        typeStatistic === TypeStatistic.CITIES ? showDiagramAndHideCountryInput() : showDiagramAndCountryInput();
    };

    const showDiagramAndHideCountryInput = () => {
        setOpenDiagram(true);
        setOpenButtonSearch(false);
    };

    const showDiagramAndCountryInput = () => {
        setOpenDiagram(true);
        setOpenButtonSearch(true);
    };

    const oneWeekStatistic = () => {
        setDateInterval(DateInterval.ONE_WEEK);
        getFilterStatistics();
    };

    const twoWeeksStatistic = () => {
        setDateInterval(DateInterval.TWO_WEEK);
        getFilterStatistics();
    };

    const oneMonthStatistic = () => {
        setDateInterval(DateInterval.ONE_MONTH);
        getFilterStatistics();
    };

    const resetDateInterval = () => {
        setDateInterval(DateInterval.DEFAULT);
        getFilterStatistics();
    };

    const getFilterStatistics = () => {
        switch (labelDiagram) {
            case TypeStatistic.COUNTRIES:
                countriesStatistic();
                break;
            case TypeStatistic.CITIES:
                citiesStatistic(TypeStatistic.CITIES);
                break;
            case TypeStatistic.CITIES_IN_COUNTRY:
                citiesStatistic(TypeStatistic.CITIES_IN_COUNTRY);
                break;
        }
    };

    const openCountryInput = () => {
        setOpenButtonSearch(true);
        setLabelDiagram(TypeStatistic.CITIES_IN_COUNTRY);
        setStatistics([]);
    };

    const getParameter = (data: string) => {
        return data === undefined ? "" : data;
    }

    const isDisabledSearchButton = (): boolean => {
        return country.length <= 2;
    };

    function getDiagramStatistic() {
        return statistics?.length !== 0 ?
            <Outlet
                context={[
                    labelDiagram,
                    statistics.map((data) => data.data),
                    statistics.map((count) => count.count)]}/>
            : <Alert data-testid="message-empty-statistic" severity="info"
                     sx={{display: "flex", justifyContent: "center"}}>
                No available data for showing weather statistic!
            </Alert>;
    }

    return (
        <div className={"statistic-page"}>
            <Typography variant="h6" className={"title-statistic"}>
                Weather statistic
            </Typography>
            <div className={"buttons-group"}>
                <div className={"filter-statistic"}>
                    <ButtonGroup variant="contained" aria-label="outlined primary button group">
                        <Button
                            onClick={countriesStatistic} component={Link} to="/countries"
                            disabled={labelDiagram === TypeStatistic.COUNTRIES}>
                            Countries
                        </Button>
                        <Button onClick={() => citiesStatistic(TypeStatistic.CITIES)} component={Link} to="/cities"
                                disabled={labelDiagram === TypeStatistic.CITIES}>
                            Cities
                        </Button>
                        <Button onClick={openCountryInput} component={Link} to="/cities/country"
                                disabled={labelDiagram === TypeStatistic.CITIES_IN_COUNTRY}>
                            Cities in country
                        </Button>
                    </ButtonGroup>
                </div>
                <div className={"date-statistic"}>
                    <ButtonGroup variant="contained" aria-label="outlined primary button group">
                        <Button
                            onClick={oneWeekStatistic}
                            disabled={dateInterval === DateInterval.ONE_WEEK}>1 week</Button>
                        <Button onClick={twoWeeksStatistic}
                                disabled={dateInterval === DateInterval.TWO_WEEK}>2 weeks</Button>
                        <Button onClick={oneMonthStatistic}
                                disabled={dateInterval === DateInterval.ONE_MONTH}>1 month</Button>
                    </ButtonGroup>
                    <ButtonGroup className={"reset-filter"} variant="contained"
                                 aria-label="outlined primary button group">
                        <Button onClick={resetDateInterval} color="error">Reset filter</Button>
                    </ButtonGroup>
                </div>
            </div>
            {openButtonSearch ?
                <div className={"get-statistic-country"}>
                    <TextField label="Country" variant="outlined"
                               error={isDisabledSearchButton()}
                               type="text" value={country}
                               onChange={(value) => {
                                   setCountry(value.target.value);
                               }} required={true}/>
                    <Button variant="contained" className={"search-statistic-button"}
                            onClick={() => citiesStatistic(TypeStatistic.CITIES_IN_COUNTRY)}
                            size={"large"}
                            color="success"
                            disabled={isDisabledSearchButton()}>Search</Button>
                </div> : <></>}
            <div className={"statistic-diagram"}>
                {openDiagram ? getDiagramStatistic() : <></>}
            </div>
        </div>
    );
};