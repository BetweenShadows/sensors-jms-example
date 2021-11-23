$(document).ready(function () {
    /*
    * Graphs holds a hashmap
    * with the following key pair comp:
    * key => device_id
    * value => object that holds graph, data, series, etc...
    * */
    let graphs = new Map();

    let stompClient = null;
    let socket = new SockJS('/notificacion_sensores_stomp');
    initConnection(stompClient, socket, graphs);

});

function textToSpan(text) {
    return "<p>" + text + "</p>";
}

function debugDeviceStatusMsg(p) {
    return "<div class='border-3 border-dark rounded mb-5'>" + textToSpan(p.id)
        + textToSpan(p.temperature) + textToSpan(p.humidity) + textToSpan(p.timestamp) + "</div>";
}

function subscribeFunction(graphs) {
    return function (message) {
        const msg = JSON.parse(message.body);
        let date = new Date(msg.timestamp);
        let id = msg.id;
        let temperature = msg.temperature;
        let humidity = msg.humidity;
        let time = date.getSeconds();
        if(!deviceGraphExists(graphs, id)) {
            addGraph(graphs, id);
        } else {
            updateGraph(graphs, id, temperature, humidity, time);
        }
    };
}

function initConnection(stompClient, socket, graphs) {
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/info_sensores', subscribeFunction(graphs));
    });
}
