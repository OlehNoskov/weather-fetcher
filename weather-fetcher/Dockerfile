FROM maven:3.6.0-jdk-11-slim
ENV KARAF_VERSION=4.3.9

COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install

ADD https://dlcdn.apache.org/karaf/${KARAF_VERSION}/apache-karaf-${KARAF_VERSION}.tar.gz /opt
RUN tar -xzf /opt/apache-karaf-${KARAF_VERSION}.tar.gz -C /opt
RUN chmod 755 /opt

COPY org.apache.karaf.features.cfg /opt/apache-karaf-${KARAF_VERSION}/etc

EXPOSE 1099 8101 44444 8181
CMD /opt/apache-karaf-${KARAF_VERSION}/bin/karaf
