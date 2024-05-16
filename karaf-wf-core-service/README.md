Apache Karaf Technology:

Running karaf-wf-core-service module:

repo-add cxf

repo-add mvn:org.hibernate/hibernate-osgi/5.4.30.Final/xml/karaf
feature:repo-add mvn:com.weather.fetcher/weather-fetcher-features/0.0.1-SNAPSHOT/xml/features

feature:install cxf
feature:install jpa hibernate
feature:install weather-fetcher-features

Then execute 'log:display' or 'bundle:list' and 'diag <ID>' for checking starting this application.