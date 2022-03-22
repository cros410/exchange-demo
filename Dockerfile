FROM maven:3.8.4-openjdk-17-slim AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/

RUN mvn -DskipTests -B -U -e clean verify 

FROM openjdk:17-alpine

WORKDIR /

COPY --from=MAVEN_BUILD /build/target/spring-boot-docker.jar /

ENTRYPOINT ["java", "-jar", "spring-boot-docker.jar"]