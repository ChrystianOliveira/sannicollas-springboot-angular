FROM gradle:7.6.1-jdk17-jammy AS builder

WORKDIR /usr/app/

COPY . .

RUN gradle clean
RUN gradle build

FROM eclipse-temurin:17.0.5_8-jre-jammy

COPY --from=builder /usr/app/build/libs/*.jar /opt/app/application.jar

CMD java -jar /opt/app/application.jar