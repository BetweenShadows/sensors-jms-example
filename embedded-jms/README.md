# JMS Server Fake Sensor Device

## About 
This is the JMS server for the fake-sensor-device. It receives all data from the devices
and pushes it through a websocket interface. 

## Compiling from source

If you are not using the docker image, you can make a fat jar from source by doing:
```bash
./gradlew shadowJar
```

The generated fat-jar should be in /build/libs folder.


## Using the docker image

There's also a docker image of this same application available at:
https://hub.docker.com/repository/docker/ivanogc99/server-fake-sensor-device

Since the application doesn't use IO, no volume is needed.