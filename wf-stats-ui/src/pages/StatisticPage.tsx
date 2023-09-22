import {Alert, Button, ButtonGroup, TextField, Typography,} from "@mui/material";
import {getCitiesStatistic, getCountriesStatistic} from "../service/Service";
import {Statistic} from "../dto/Statistic";
import {DiagramPage} from "./DiagramPage";
import React, {useEffect, useState} from 'react';
import {DateInterval} from "../enum/DateInterval";
import {TypeStatistic} from "../enum/TypeStatistic";

export default function StatisticPage() {
    const [statistics, setStatistics] = useState<Statistic[]>();
    const [dateInterval, setDateInterval] = useState<string>('');
    const [labelDiagram, setLabelDiagram] = useState<TypeStatistic>(TypeStatistic.COUNTRIES);
    const [country, setCountry] = useState<string>('');
    const [openStatistic, setOpenStatistic] = useState<boolean>(false);
    const [openButtonSearch, setOpenButtonSearch] = useState<boolean>(false);

    useEffect(() => {
        window.localStorage.setItem("country", country!);
    }, [country]);

    const countriesStatistic = () => {
        setLabelDiagram(TypeStatistic.COUNTRIES);
        getCountriesStatistic(dateInterval).then((response) => setStatistics(response));
        setOpenStatistic(true);
        setOpenButtonSearch(false);
    };

    const citiesStatistic = () => {
        setLabelDiagram(TypeStatistic.CITIES);
        getCitiesStatistic(country, dateInterval).then((response) => setStatistics(response));
        setOpenStatistic(true);
        setOpenButtonSearch(false);
    };

    const citiesByCountryStatistic = () => {
        setLabelDiagram(TypeStatistic.CITIES);
        getCitiesStatistic(country, dateInterval).then((response) => setStatistics(response));
        setOpenStatistic(true);
        openCountryInputField();
    };

    const openCountryInputField = () => {
        setOpenButtonSearch(true);
    };

    const oneWeekStatistic = () => {
        setDateInterval(DateInterval.ONE_WEEK);
        labelDiagram === TypeStatistic.COUNTRIES ? countriesStatistic() : citiesStatistic();
    };

    const twoWeeksStatistic = () => {
        setDateInterval(DateInterval.TWO_WEEK);
        labelDiagram === TypeStatistic.COUNTRIES ? countriesStatistic() : citiesStatistic();
    };

    const oneMonthStatistic = () => {
        setDateInterval(DateInterval.ONE_MONTH);
        labelDiagram === TypeStatistic.COUNTRIES ? countriesStatistic() : citiesStatistic();
    };

    const resetDateInterval = () => {
        setDateInterval('');
        labelDiagram === TypeStatistic.COUNTRIES ? countriesStatistic() : citiesStatistic();
    };

    const isDisabledButton = (): boolean => {
        return country?.length <= 2;
    };

    const isValidCountry = (): boolean => {
        // @ts-ignore
        return country?.length <= 2;
    };

    function getStatistic() {
        return statistics?.length !== 0 ?
            <DiagramPage data={statistics?.map((data) => data.data)}
                         count={statistics?.map((count) => count.count)}
                         labelDiagram={labelDiagram}/>
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
                        <Button onClick={countriesStatistic}>Countries</Button>
                        <Button onClick={citiesStatistic}>Cities</Button>
                        <Button onClick={openCountryInputField}>Cities in country</Button>
                    </ButtonGroup>
                </div>
                <div className={"date-statistic"}>
                    <ButtonGroup variant="contained" aria-label="outlined primary button group">
                        <Button onClick={oneWeekStatistic}>1 week</Button>
                        <Button onClick={twoWeeksStatistic}>2 weeks</Button>
                        <Button onClick={oneMonthStatistic}>1 month</Button>
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
                {openStatistic ? getStatistic() : <></>}
            </div>
        </div>
    );
};