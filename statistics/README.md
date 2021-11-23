# Statistics (Simple JMS Consumer & Web Application)

## About
This is the consumer and the web application that graphs all the device's data. It consumes the 
data of RabbitMQ Server and then sends it through WebSockets so that the Web Application can use it.

## Compiling from source

If you are not using the docker image, you can make a fat jar from source by doing:
```bash
./gradlew bootJar
```

The generated fat-jar should be in /build/libs folder.

## Running the application
In order to run the fatjar, you must provide the following environment variables:

- RMQ_USER=RabbitMQ User
- RMQ_PASS=RabbitMQ Password
- RMQ_HOST=RabbitMQ Host
- RMQ_PORT=RabbitMQ Port
- APP_PORT=Application Port

A basic example of environment variables would be:

```dotenv
RMQ_USER=guest
RMQ_PASS=guest
RMQ_HOST=localhost
RMQ_PORT=5672
APP_PORT=8080
```

## Using the docker image

There's also a docker image of this same application available at:
https://hub.docker.com/repository/docker/ivanogc99/statistics_device
