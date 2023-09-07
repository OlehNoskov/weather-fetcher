import {resolve} from 'path'

export const entry = './index.tsx'
export const module = {
    rules: [
        {test: /\.svg$/, use: 'svg-inline-loader'},
        {test: /\.css$/, use: ['style-loader', 'css-loader']},
        {test: /\.(js)$/, use: 'babel-loader'}
    ]
}
export const output = {
    path: resolve(__dirname, 'dist'),
    filename: 'index_bundle.js'
}