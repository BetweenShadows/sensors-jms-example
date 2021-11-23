function deviceGraphExists(graphs, id) {
    return graphs.get(id) != null;
}

function updateGraph(graphs, id, temperature, humidity, time) {
    if(!deviceGraphExists(graphs, id)) {
        return;
    }
    let value = graphs.get(id);

    value.tempData.labels.push(time);
    value.tempData.datasets[0].data.push(temperature);
    value.tempChart.update();

    value.humidityData.labels.push(time);
    value.humidityData.datasets[0].data.push(humidity);
    value.humidityChart.update();
}

function addGraph(graphs, id) {
    let deviceDiv = $('#devices');

    // Temperature Graph Creation...
    deviceDiv.append("<canvas id='temperature-" + id + "' width='600' height='400' class='mb-5'></canvas>");
    let temperatureCanvas = document.getElementById('temperature-' + id).getContext('2d');
    let temperatureData = createGraphData('Temperature');
    let temperatureGraphOptions = createGraphOptions('Temperature vs Time (device ' + id + ')', '°C', 's', 'Temperature', 'Time');
    let temperatureGraphDrawing = new Chart(temperatureCanvas, {
        type: 'line',
        data: temperatureData,
        options: temperatureGraphOptions
    });

    // Humidity Graph creation...
    deviceDiv.append("<canvas id='humidity-" + id + "' width='600' height='400' class='mb-5'></canvas>");
    let humidityCanvas = document.getElementById('humidity-' + id).getContext('2d');
    let humidityData = createGraphData('Relative Humidity');
    let humidityGraphOptions = createGraphOptions('Relative Humidity vs Time (device ' + id + ')', '', 's', 'Temperature', 'Time');
    let humidityGraphDrawing = new Chart(humidityCanvas, {
        type: 'line',
        data: humidityData,
        options: humidityGraphOptions
    });

    let value = {
        tempData: temperatureData,
        tempChart: temperatureGraphDrawing,
        humidityData: humidityData,
        humidityChart: humidityGraphDrawing
    };

    graphs.set(id, value);
}

function createGraphData(myLabel) {
    return {
        labels: [],
        datasets: [
            {
                label: myLabel,
                fill: true,
                data: []
            }
        ]
    };
}

function createGraphOptions(title, yScale, xScale, yLabel, xLabel) {
    return {
        plugins: {
            title: {
                display: true,
                text: title
            }
        },
        scales: {
            y: {
                label: yLabel,
                ticks: {
                    callback: function (value, index, values) {
                        return value + yScale;
                    }
                }
            },
            x: {
                label: xLabel,
                ticks: {
                    callback: function (value, index, values) {
                        return value + xScale;
                    }
                }
            }
        },
        animation: true,
        animationSteps: 100,
        animationEasing: "easeOutQuart",
        scaleFontSize: 16,
        responsive: false,
        showTooltip: true,
        tooltipTemplate: "<%if (label){%><%=label%>: <%}%><%= value %>",
        scaleShowGridLines: false,
        bezierCurve: false,
        pointDotRadius: 5,
    };
}