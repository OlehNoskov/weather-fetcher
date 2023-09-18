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
import {Line} from 'react-chartjs-2';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Filler,
    Legend
);

interface IDiagramPage {
    data: string[] | undefined,
    count: number[] | undefined,
    labelDiagram: string
}

export function DiagramPage(props: IDiagramPage) {
    const labels = props.data;

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
                fill: true,
                label: props.labelDiagram,
                data: props.count?.map((count) => count),
                borderColor: 'rgb(53, 162, 235)',
                backgroundColor: 'rgba(53, 162, 235, 0.5)',
            },
        ],
    };

    return <Line options={options} data={data}/>;
}
