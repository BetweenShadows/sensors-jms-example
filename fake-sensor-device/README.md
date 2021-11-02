# Fake Sensor Device

## About 
This is the client that simulates the company's sensor devices. It generates random data for a certain amount
of time (by default it's every minute). 

## Compiling from source

If you are not using the docker image, you can make a fat jar from source by doing:
```bash
./gradlew shadowJar
```

The generated fat-jar should be in /build/libs folder.


## Running the application 
In order to execute the fat-jar properly, you must provide the following environment variables:

- DEVICE_ID = This is the identifier of the sensor device. Must be an integer number.
- BROKER_URL = The URL of the JMS server.
- JMS_USER = User of the JMS Server.
- JMS_PASS = Password for the user of JMS Server.
- QUEUE_NAME = The name of the queue you will be using.
- SEND_INFO_EVERY_MS = The period of time which the device sends data. 

A basic example of environment variables would be:

```dotenv
DEVICE_ID=1
BROKER_URL=tcp://0.0.0.0:61616
JMS_USER=admin
JMS_PASS=admin
QUEUE_NAME=notificacion_sensores
SEND_INFO_EVERY_MS=60000
```

## Using the docker image

There's also a docker image of this same application available at: 
https://hub.docker.com/repository/docker/ivanogc99/fake-sensor-device

Since the application doesn't use IO, no volume is needed. Just make sure to add the environment variables
before running the app.