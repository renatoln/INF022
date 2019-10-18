let getBubbleChartData = function () {
    return [
        ['ID', 'Life Expectancy', 'Fertility Rate', 'Region',     'Population'],
        ['CAN',    80.66,              1.67,      124,  33739900],
        ['DEU',    79.84,              1.36,      124,         81902307],
        ['DNK',    78.6,               1.84,      333,         5523095],
        ['EGY',    72.73,              2.78,      12345,    79716203],
        ['GBR',    80.05,              2,         1235,         61801570],
        ['IRN',    72.49,              1.7,       123,    73137148],
        ['IRQ',    68.09,              4.77,      312,    31090763],
        ['ISR',    81.55,              2.96,      111,    7485600],
        ['RUS',    68.6,               1.54,      111,         141850000],
        ['USA',    78.09,              2.05,      321,  307007000]
    ];
};

function atualizaBubbleChart(element_id) {

    data = getBubbleChartData();
    ShowBubbleChart(element_id, data);
}

function ShowBubbleChart(element_id, data) {
    data = google.visualization.arrayToDataTable(data);

    let options = {
        colorAxis: {colors: ['yellow', 'red']}
    };

    let chart = new google.visualization.BubbleChart(document.getElementById(element_id));
    chart.draw(data, options);
}

