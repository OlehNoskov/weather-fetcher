Technology stack:
- Spring Boot
- Spring JPA
- Apache CXF
- React
- Typescript
- Webpack
- MariaDB
- Docker

===== MODULES =====

Backend:

 - wf-core-services - service that provides a set of endpoints for requesting weather for specified location. (Port:8080)

 - wf-stats-services - a set of endpoints (Backend For Frontend pattern aka BFF) developed in backend-for-frontend 
style that provides stats to display in wf-stats-ui. (Port:8081)

Frontend:

- wf-statistic-ui - primary UI to fetch weathers information and show stats. (Port:3000)

- wf-location-ui - remote UI module for wf-statistic-ui application. (Port:3001)

For running only database in docker you can use command:
    docker compose -f docker-compose-database.yml up

For running react application in wf-statistic-ui you need use this command:
    npm i
    npm start
    npm run test - for running UI tests

For running remote react module in wf-location-ui you need use this command:
    npm i
    npm start
    npm run test - for running UI tests

For running project in docker you can use command (backend part and database):
    docker compose up