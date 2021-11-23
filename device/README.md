# Sensor Device (Simple JMS Publisher)

## About
This is the client that simulates the company's sensor devices. It generates random data for a certain amount
of time  No volume is needed since it won't store any data.

## Compiling from source

If you are not using the docker image, you can make a fat jar from source by doing:
```bash
./gradlew bootJar
```

The generated fat-jar should be in /build/libs folder.

## Running the application
In order to run the fatjar, you must provide the following environment variables:

- RMQ_USER= User of RabbitMQ
- RMQ_PASS= Password of RabbitMQ
- RMQ_HOST= Host of RabbitMQ
- RMQ_PORT= Port of RabbitMQ
- DEVICE_ID= ID of the device
- SEND_RATE= Status message sending rate of the device. It's in MS.

A basic example of environment variables would be:

```dotenv
RMQ_USER=guest
RMQ_PASS=guest
RMQ_HOST=rabbitmq_server
RMQ_PORT=5672
DEVICE_ID=1
SEND_RATE=3000
```

## Using the docker image

There's also a docker image of this same application available at:
https://hub.docker.com/repository/docker/ivanogc99/sensor_device