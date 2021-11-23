# Sensors JMS Example

## About

A company specializing in the development of electronic sensors requires our services to communicate efficiently.
The information of each device is displayed through a web application. Each endpoint implements a JSON frame with
the following schema:

```json 
{
    "date": "Generated date in format DD/MM/YYYY HH:mm:ss",
    "device_id": "Number",
    "temperature": "Number",
    "humidity": "Number"
}
```

To send the information between the devices, a server implementing the JMS standard will be used with a message-oriented
protocol (OpenWire, MQTT, AMQP) using a publish/subscribe type queue, called  sensor_notification. Each device processes
information every minute and sends it to the distribution queue, then the server takes the message and persists it in
the database. The application must graph the data in real-time.

## Requirements 
- Develop a client that simulates the endpoints. You must instantiate at least two clients for tests.
- Implement a server that supports JMS and a message-oriented protocol which initializes the distribution queue of type publish/subscribe. 
  It may be an application with the protocol in embedded form or a server designed for those
  purposes (Apache ActiveMQ, RabbitMQ, Mosquitto, among others).
- Make a web application that visualizes the processed data through graphs
  of the type line in real time, where the information of the temperature and
  humidity vs time (separate graphs), for each of the sensors
  connected. Communication between the web application and the client can be using
  the WebSocket protocol to present update information.
- The Scenario must use Docker and Docker Compose.

## Preparing the Scenario

To run the proposed scenario, just use the following command:
```sh 
docker-compose --e .env up -d
```
