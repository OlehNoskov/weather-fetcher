import React, {useState} from "react";
import {Forecast} from "../dto/Forecast";
import {ScaleSymbols} from "../enum/ScaleSymbols";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";

interface ForecastProps {
    forecast?: Forecast;
}

export default function ShowForecast(props: ForecastProps) {
    const [scaleSymbol, setScaleSymbol] = useState<ScaleSymbols>(ScaleSymbols.CELSIUS);

    return (
        <TableContainer component={Paper}>
            <Table size="medium">
                <TableHead>
                    <TableRow>
                        <TableCell align="center">Country</TableCell>
                        <TableCell align="center">City</TableCell>
                        <TableCell align="center">Overall</TableCell>
                        <TableCell align="center">Degrees {scaleSymbol}</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    <TableRow>
                        <TableCell align="center">{props.forecast?.country}</TableCell>
                        <TableCell align="center">{props.forecast?.city}</TableCell>
                        <TableCell align="center">{props.forecast?.weather.overall}</TableCell>
                        <TableCell align="center">{props.forecast?.weather.temperature.degrees}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    );
};