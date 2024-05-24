## Build

You should you java 11 version, execute this command if you have sdk man:

```
sdk use java 11.0.19-zulu

```

The build uses Apache Maven. Simply use:

```
mvn clean install
```

## Feature and Deployment

On a running Karaf instance, register the features repository using:

```
karaf@root()> feature:repo-add mvn:org.weather/weather-fetcher-features/1.0-SNAPSHOT/xml/features
```

Then, you can install the datasource feature:

```
karaf@root()> feature:install weather-fetcher-features
```

Or you can update org.apache.karaf.features.cfg in karaf folder: etc/org.apache.karaf.features.cfg 
which is located in current module