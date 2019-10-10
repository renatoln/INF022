let getGaugeData = function () {
    return [
        ['Label', 'Value'],
        ['Memory', 80],
        ['CPU', 55],
        ['Network', 68]
    ];
};

function atualizaGauge(element_id) {
    data = getGaugeData();
    ShowGauge(element_id, data);
}

let ShowGauge = function (element_id, data) {
    data = google.visualization.arrayToDataTable(data);

    let options = {
        width: 400, height: 120,
        redFrom: 90, redTo: 100,
        yellowFrom:75, yellowTo: 90,
        greenFrom:50, greenTo:75,
        minorTicks: 5
    };

    let chart = new google.visualization.Gauge(document.getElementById(element_id));

    chart.draw(data, options);

    for(let i = 0;  i < data.length; i++)
    {
        setInterval(function() {
            data.setValue(i, 1, Math.round(100));
            chart.draw(data, options);
        }, 100);
    }
};
