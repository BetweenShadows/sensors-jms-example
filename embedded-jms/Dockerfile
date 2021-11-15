FROM gradle:7.2.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

LABEL org.wolfisc.fakesensordevice.version="1.0"
LABEL org.wolfisc.fakesensordevice.name="Embedded ActiveMQ Server For Fake Sensor Device"
LABEL org.wolfisc.fakesensordevice.name="Server that will receive data of sensor devices."
LABEL org.wolfisc.fakesensordevice.authors="Ivanosevic Garcia<20170874@ce.pucmm.edu.do>, Jean Carlos George<20170167@ce.pucmm.edu.do>"

FROM openjdk:11.0.12-jre-slim-buster
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/activemqserver.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/activemqserver.jar"]