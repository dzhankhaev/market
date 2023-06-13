FROM eclipse-temurin:17-jre-alpine

RUN mkdir market
WORKDIR market

COPY target/market-0.0.1-SNAPSHOT.jar /market/
COPY wait-for.sh /market/
RUN chmod +x wait-for.sh

#CMD ["java", "-jar", "market-0.0.1-SNAPSHOT.jar"]