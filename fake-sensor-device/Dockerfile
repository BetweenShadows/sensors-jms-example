FROM gradle:7.2.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

LABEL org.wolfisc.fakesensordevice.version="1.0"
LABEL org.wolfisc.fakesensordevice.name="Fake Sensor Device"
LABEL org.wolfisc.fakesensordevice.name="This is an application that simulates a sensor device sending data through JMS with activemq."
LABEL org.wolfisc.fakesensordevice.authors="Ivanosevic Garcia<20170874@ce.pucmm.edu.do>, Jean Carlos George<20170167@ce.pucmm.edu.do>"

FROM openjdk:11.0.12-jre-slim-buster
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/mi-app-multi-stage.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/mi-app-multi-stage.jar"]