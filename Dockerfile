FROM gradle:8.7.0-jdk21-alpine as build

WORKDIR /build

COPY . .

RUN gradle bootJar

FROM eclipse-temurin:18 as exec

WORKDIR /app

COPY --from=build /build/build/libs/solrj-spring-0.0.1-SNAPSHOT.jar solrj-cli.jar

ENTRYPOINT ["java", "-jar", "solrj-cli.jar", "--Dcli.solrhostname=solr"]