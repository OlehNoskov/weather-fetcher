import {Alert, Button, ButtonGroup, TextField, Typography,} from "@mui/material";
import {getCitiesStatistic, getCountriesStatistic} from "../service/Service";
import {Statistic} from "../dto/Statistic";
import React, {useEffect, useState} from 'react';
import {DateInterval} from "../enum/DateInterval";
import {TypeStatistic} from "../enum/TypeStatistic";
import {Link, Outlet} from "react-router-dom";

export default function StatisticPage() {
    const [statistics, setStatistics] = useState<Statistic[]>([]);
    const [dateInterval, setDateInterval] = useState<DateInterval>(DateInterval.UNKNOWN);
    const [labelDiagram, setLabelDiagram] = useState<TypeStatistic>(TypeStatistic.COUNTRIES);
    const [country, setCountry] = useState<string>('');
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
            case DateInterval.UNKNOWN:
                resetDateInterval();
                break;
        }
    }, [dateInterval]);

    const countriesStatistic = () => {
        setLabelDiagram(TypeStatistic.COUNTRIES);
        getCountriesStatistic(dateInterval).then((response) => setStatistics(response));
        setOpenDiagram(true);
        setOpenButtonSearch(false);
    };

    const citiesStatistic = () => {
        setLabelDiagram(TypeStatistic.CITIES);
        getCitiesStatistic('', dateInterval).then((response) => setStatistics(response));
        setOpenDiagram(true);
        setOpenButtonSearch(false);
    };

    const citiesByCountryStatistic = () => {
        setLabelDiagram(TypeStatistic.CITIES_IN_COUNTRY);
        getCitiesStatistic(country, dateInterval).then((response) => setStatistics(response));
        setOpenDiagram(true);
        setOpenButtonSearch(true);
    };

    const oneWeekStatistic = () => {
        setDateInterval(DateInterval.ONE_WEEK);
        getStatistics();
    };

    const twoWeeksStatistic = () => {
        setDateInterval(DateInterval.TWO_WEEK);
        getStatistics();
    };

    const oneMonthStatistic = () => {
        setDateInterval(DateInterval.ONE_MONTH);
        getStatistics();
    };

    const resetDateInterval = () => {
        setDateInterval(DateInterval.UNKNOWN);
        getStatistics();
    };

    const isDisabledButton = (): boolean => {
        return country?.length <= 2;
    };

    const isValidCountry = (): boolean => {
        // @ts-ignore
        return country?.length <= 2;
    };

    const changeButtonState = () => {
        setOpenButtonSearch(true);
        setLabelDiagram(TypeStatistic.CITIES_IN_COUNTRY);
    };

    const getStatistics = () => {
        switch (labelDiagram) {
            case TypeStatistic.COUNTRIES:
                countriesStatistic();
                break;
            case TypeStatistic.CITIES:
                citiesStatistic();
                break;
            case TypeStatistic.CITIES_IN_COUNTRY:
                citiesByCountryStatistic();
                break;
        }
    };

    function getDiagramStatistic() {
        return statistics?.length !== 0 ?
            <Outlet
                context={[
                    labelDiagram,
                    statistics.map((data) => data.data),
                    statistics.map((count) => count.count)]}/>
            : <Alert severity="info">
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
                        <Button onClick={countriesStatistic} component={Link} to="/countries"
                                color={labelDiagram === TypeStatistic.COUNTRIES ? 'success' : 'primary'}>
                            Countries
                        </Button>
                        <Button onClick={citiesStatistic} component={Link} to="/cities"
                                color={labelDiagram === TypeStatistic.CITIES ? 'success' : 'primary'}>
                            Cities
                        </Button>
                        <Button onClick={changeButtonState} component={Link} to="/cities/country"
                                color={labelDiagram === TypeStatistic.CITIES_IN_COUNTRY ? 'success' : 'primary'}>
                            Cities in country
                        </Button>
                    </ButtonGroup>
                </div>
                <div className={"date-statistic"}>
                    <ButtonGroup variant="contained" aria-label="outlined primary button group">
                        <Button
                            onClick={oneWeekStatistic}
                            color={dateInterval === DateInterval.ONE_WEEK ? 'success' : 'primary'}>1 week</Button>
                        <Button onClick={twoWeeksStatistic}
                                color={dateInterval === DateInterval.TWO_WEEK ? 'success' : 'primary'}>2 weeks</Button>
                        <Button onClick={oneMonthStatistic}
                                color={dateInterval === DateInterval.ONE_MONTH ? 'success' : 'primary'}>1 month</Button>
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
                               error={isValidCountry()}
                               type="text" value={country}
                               onChange={(value) => {
                                   setCountry(value.target.value);
                               }} required={true}/>
                    <Button variant="contained" className={"search-statistic-button"}
                            onClick={citiesByCountryStatistic}
                            size={"large"}
                            color="success"
                            disabled={isDisabledButton()}>Search</Button></div> : <></>}
            <div className={"statistic-diagram"}>
                {openDiagram ? getDiagramStatistic() : <></>}
            </div>
        </div>
    );
};