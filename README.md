Technology stack:
- Spring Boot
- Spring JPA
- Apache CXF
- React
- Typescript
- Webpack

modules: 
 - wf-core-services - service that provides a set of endpoints for requesting weather for specified location.

 - wf-stats-shared-components - provides a UI React component (Microfrontends approach aka MFE) for specifying 
location on map and returns weather for the same (simplified variant can just use input fields or dropdown).

 - wf-stats-services - a set of endpoints (Backend For Frontend pattern aka BFF) developed in backend-for-frontend 
style that provides stats to display in wf-stats-ui.

- wf-stats-ui - primary UI to fetch weathers information and show stats.

For running only database in docker you can use command:
    docker compose -f docker-compose-database.yml up

For running react application in wf-stats-ui you need use this command:
    npm start

For running project in docker you can use command:
    docker compose up