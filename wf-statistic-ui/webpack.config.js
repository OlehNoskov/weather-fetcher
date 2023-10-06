const HtmlWebpackPlugin = require("html-webpack-plugin");
const {ModuleFederationPlugin} = require("webpack").container;
const path = require("path");
const deps = require("./package.json").dependencies;

module.exports = {
    entry: "./src/index.ts",
    mode: "development",
    devServer: {
        port: 3000,
        open: true,
        historyApiFallback: true,
        headers: {
            "Access-Control-Allow-Origin": "*",
        },
        proxy: {
            "/statistic": {
                secure: false,
                changeOrigin: true,
                target: "http://localhost:8081"
            },
            "/weather": {
                secure: false,
                changeOrigin: true,
                target: "http://localhost:8080"
            }
        },
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
        ],
    },
    plugins: [
        new ModuleFederationPlugin({
            name: "container",
            filename: "remoteEntry.js",
            remotes: {
                Location: "remote@http://localhost:3001/remoteEntry.js"
            },
            shared: [{react: deps.react, "react-dom": deps["react-dom"]}],
        }),
        new HtmlWebpackPlugin({
            template: "./public/index.html",
        }),
    ],
};
