## Build

For starting this application you should install apache-karaf:

```
https://dlcdn.apache.org/karaf/4.4.6/apache-karaf-4.4.6.tar.gz

```

Also, you should use java 11 version, execute this command if you have sdk man:

```
sdk use java 11.0.19-zulu

```

The build uses Apache Maven. Simply use:

```
mvn clean install

```

For running apache-karaf go to the folder and execute this command:

```
bin/karaf clean

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

OR

you can update org.apache.karaf.features.cfg in karaf folder like this file 'etc/org.apache.karaf.features.cfg'
which is located in current module.