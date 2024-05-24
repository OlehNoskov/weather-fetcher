const HtmlWebpackPlugin = require("html-webpack-plugin");
const {ModuleFederationPlugin} = require("webpack").container;
const path = require("path");
const deps = require("./package.json").dependencies;

module.exports = {
    entry: "./src/index.ts",
    mode: "development",
    devServer: {
        port: 3001,
        open: true,
        headers: {
            "Access-Control-Allow-Origin": "*",
        },
        proxy: {
            "/weather": {
                secure: false,
                changeOrigin: true,
                target: "http://localhost:8181/cxf"
            }
        },
    },
    output: {
        publicPath: "auto",
    },
    resolve: {
        extensions: [".ts", ".tsx", ".js", ".css", ".json"],
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx|tsx|ts)$/,
                loader: "ts-loader",
                exclude: /node_modules/,
            },
            {
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
            },
            {
                test: /\.json$/,
                loader: "json-loader"
            }
        ],
    },
    plugins: [
        new ModuleFederationPlugin({
            name: "remote",
            filename: "remoteEntry.js",
            exposes: {
                "./LocationPage": "./src/pages/LocationPage",
            },
            shared: [{react: deps.react, "react-dom": deps["react-dom"]}],
        }),
        new HtmlWebpackPlugin({
            template: "./public/index.html",
        }),
    ],
};
