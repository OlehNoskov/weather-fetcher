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
                        <TableCell align="center" className={"cell-header"}>Country</TableCell>
                        <TableCell align="center" className={"cell-header"}>City</TableCell>
                        <TableCell align="center" className={"cell-header"}>Overall</TableCell>
                        <TableCell align="center" className={"cell-header"}>Degrees {scaleSymbol}</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    <TableRow>
                        <TableCell align="center" className={"cell-data"}>{props.forecast?.country}</TableCell>
                        <TableCell align="center" className={"cell-data"}>{props.forecast?.city}</TableCell>
                        <TableCell align="center" className={"cell-data"}>{props.forecast?.weather.overall}</TableCell>
                        <TableCell align="center" className={"cell-data"}>{props.forecast?.weather.temperature.degrees}
                        </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    );
};