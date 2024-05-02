DROP TABLE IF EXISTS temperature;
DROP TABLE IF EXISTS weather;
DROP TABLE IF EXISTS forecast;

CREATE TABLE temperature
(
    id      SERIAL PRIMARY KEY,
    scale   INTEGER NOT NULL,
    degrees INTEGER NOT NULL
);

CREATE TABLE weather
(
    id             SERIAL PRIMARY KEY,
    overall        INTEGER NOT NULL,
    temperature_id INTEGER NOT NULL
);

CREATE TABLE forecast
(
    id         SERIAL PRIMARY KEY,
    country    VARCHAR(100) NOT NULL,
    city       VARCHAR(100) NOT NULL,
    date       TIMESTAMP    NOT NULL,
    weather_id INTEGER      NOT NULL
);

SET FOREIGN_KEY_CHECKS=1