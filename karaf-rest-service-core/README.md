## Build

The build uses Apache Maven. Simply use:

```
mvn clean install
```

## Feature and Deployment

On a running Karaf instance, register the features repository using:

```
karaf@root()> feature:repo-add mvn:com.weather.fetcher/karaf-rest-features/LATEST/xml/features
```

Then, you can install the service blueprint provider:

```
karaf@root()> feature:install weather-fetcher-features
```