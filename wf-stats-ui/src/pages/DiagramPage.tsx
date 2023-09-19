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
                label: props.labelDiagram,
                data: props.count?.map((count) => count),
                backgroundColor: 'rgba(53, 162, 235, 0.5)',
            }
        ],
    };

    return <Bar options={options} data={data}/>;
}
