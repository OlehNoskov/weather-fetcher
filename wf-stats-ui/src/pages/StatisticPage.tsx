import {
    Button, ButtonGroup, TextField,
} from "@mui/material";
import {getCitiesByCountryStatistic, getCitiesStatistic, getCountriesStatistic} from "../service/Service";
import {Statistic} from "../dto/Statistic";
import {DiagramPage} from "./DiagramPage";
import React, {useEffect, useState} from 'react';

export default function StatisticPage() {
    const [statistics, setStatistics] = useState<Statistic[]>();
    const [openStatistic, setOpenStatistic] = useState<boolean>(false);
    const [labelDiagram, setLabelDiagram] = useState<string>('');
    const [openButtonSearch, setOpenButtonSearch] = useState<boolean>(false);
    // const [country, setCountry] = useState(window.localStorage.getItem("country"));
    const [country, setCountry] = useState<string>('');

    useEffect(() => {
        window.localStorage.setItem("country", country!);
    }, [country]);

    const topCountriesStatistic = () => {
        getCountriesStatistic().then((response) => setStatistics(response));
        setOpenStatistic(true);
        setOpenButtonSearch(false);
        setLabelDiagram('Countries')
    };

    const topCitiesStatistic = () => {
        getCitiesStatistic().then((response) => setStatistics(response));
        setOpenStatistic(true);
        setOpenButtonSearch(false);
        setLabelDiagram('Cities')
    };

    const topCitiesByCountryStatistic = () => {
        getCitiesByCountryStatistic(country).then((response) => setStatistics(response));
        setOpenStatistic(true);
        setOpenButtonSearch(true);
        setLabelDiagram('Cities')
    };

    const test = () => {
        setOpenButtonSearch(true);
    };

    return (
        <div>
            <h1 className={"title-statistic"}>Weather Statistic</h1>
            <ButtonGroup variant="contained" aria-label="outlined primary button group">
                <Button onClick={topCountriesStatistic}>Countries</Button>
                <Button onClick={topCitiesStatistic}>Cities</Button>
                <Button onClick={test}>Cities in country</Button>
            </ButtonGroup>
            {openButtonSearch ?
                <div className={"get-statistic"}>
                    <TextField label="Country" variant="outlined"
                               type="text" value={country}
                               onChange={(value) => {
                                   setCountry(value.target.value);
                               }} required={true}/>
                    <Button variant="contained" className={"search-statistic-button"}
                            onClick={topCitiesByCountryStatistic}
                            size={"large"}
                            color="success">Search
                    </Button>
                </div> : null}
            <div className={"statistic-diagram"}>
                {openStatistic ?
                    <DiagramPage data={statistics?.map((data) => data.data)}
                                 count={statistics?.map((count) => count.count)}
                                 labelDiagram={labelDiagram}/> : null}
            </div>
        </div>
    );
};