import React from 'react';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Filler,
    Legend,
} from 'chart.js';
import {Bar} from 'react-chartjs-2';
import {Chart, registerables} from 'chart.js'
import {Card} from "@mui/material";
import {useOutletContext} from "react-router-dom";

Chart.register(...registerables)

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Filler,
    Legend,
);

export function DiagramPage() {
    const dataDiagram: any = useOutletContext();
    const label: string = dataDiagram[0];
    const labels: string [] = dataDiagram[1];
    const counts: number[] = dataDiagram[2];

    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top' as const,
            },
        },
    };

    const data = {
        labels,
        datasets: [
            {
                label: label,
                data: counts?.map((count) => count),
                backgroundColor: 'rgba(53, 162, 235, 0.5)',
            }
        ],
    };

    return (
        <Card sx={{padding: 4}}>
            <Bar options={options} data={data}/>
        </Card>
    );
}
