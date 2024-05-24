DROP TABLE IF EXISTS forecast;

CREATE TABLE forecast
(
    id         SERIAL PRIMARY KEY,
    country    VARCHAR(100) NOT NULL,
    city       VARCHAR(100) NOT NULL,
    date       TIMESTAMP    NOT NULL,
    overall    VARCHAR(100)      NOT NULL,
    scale      VARCHAR(100)     NOT NULL,
    degrees    INTEGER      NOT NULL

);