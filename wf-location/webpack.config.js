const HtmlWebpackPlugin = require("html-webpack-plugin");
const {ModuleFederationPlugin} = require("webpack").container;
const path = require("path");

module.exports = {
    entry: "./src/index.tsx",
    mode: "development",
    devServer: {
        port: 3001,
        open: true,
    },
    resolve: {
        extensions: [".ts", ".tsx", ".js"],
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx|tsx|ts|css)$/,
                loader: "ts-loader",
                exclude: /node_modules/,
            },
        ],
    },
    plugins: [
        new ModuleFederationPlugin({
            name: "location",
            filename: "remoteEntry.js",
            exposes: {
                "./Test": "./src/components/Test",
            },
            shared: {
                react: {singleton: true, eager: true},
                "react-dom": {
                    singleton: true,
                    eager: true,
                },
                "react-router-dom": {
                    singleton: true,
                    eager: true,
                },
            },
        }),
        new HtmlWebpackPlugin({
            template: "./public/index.html",
        }),
    ],
};
