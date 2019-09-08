function lineChart(periodo, valor, cidade) {
  var trace1 = {
    x: periodo,
    y: valor,
    type: 'scatter'
  };

  var layout = {
    title: cidade,
    showlegend: false
  };


  var data = [trace1];

  Plotly.newPlot('myDiv', data, layout);
}

/****************************************************************************************************************/
/****************************************************************************************************************/
/*****************************************Menu*******************************************************************/
/****************************************************************************************************************/

function barChart(periodo, valor, cidade) {
  var data = [{
    x: periodo,
    y: valor,
    type: 'bar'
  }];

  var layout = {
    title: cidade,
    showlegend: false
  };

  Plotly.newPlot('myDiv', data, layout);
}


/****************************************************************************************************************/
/****************************************************************************************************************/
/*****************************************Menu*******************************************************************/
/****************************************************************************************************************/

function ponto() {
  var x1 = [];
  var x2 = [];
  var y1 = [];
  var y2 = [];
  for (var i = 1; i < 500; i++) {
    k = Math.random();
    x1.push(k * 5);
    x2.push(k * 10);
    y1.push(k);
    y2.push(k * 2);
  }
  var trace1 = {
    x: x1,
    y: y1,
    name: 'control',
    autobinx: false,
    histnorm: "count",
    marker: {
      color: "rgba(255, 100, 102, 0.7)",
      line: {
        color: "rgba(255, 100, 102, 1)",
        width: 1
      }
    },
    opacity: 0.5,
    type: "histogram",
    xbins: {
      end: 2.8,
      size: 0.06,
      start: .5
    }
  };
  var trace2 = {
    x: x2,
    y: y2,
    autobinx: false,
    marker: {
      color: "rgba(100, 200, 102, 0.7)",
      line: {
        color: "rgba(100, 200, 102, 1)",
        width: 1
      }
    },
    name: "experimental",
    opacity: 0.75,
    type: "histogram",
    xbins: {
      end: 4,
      size: 0.06,
      start: -3.2

    }
  };
  var data = [trace1, trace2];
  var layout = {
    bargap: 0.05,
    bargroupgap: 0.2,
    barmode: "overlay",
    title: "Sampled Results",
    xaxis: { title: "Value" },
    yaxis: { title: "Count" }
  };
  Plotly.newPlot('myDiv', data, layout);
}
/* Atributo para plotar ; localizacao = meso ou micro */
/* Plota todos os municipios dado atributo e localizacao */
/* Se meso, plota agrupando por micro e muni; Se micro, plota agrupando por muni */
function sunburst(localizacao) {

  var layout = {
    margin: { l: 0, r: 0, b: 0, t: 0 },
    width: 500,
    height: 500
  };

  if (!localizacao) {
    return;
  }

  var toUseMeso = false;

  var idMeso = 0;
  var valueMeso = 0;

  var idMicro = 0;
  var valueMicro = 0;

  var labels = new Array();
  var parents = new Array();
  var values = new Array();

  parents.push("");

  for (i in geral.MESORREGIOES) {
    if (geral.MESORREGIOES[i].NOME_MESORREGIAO === localizacao) {

      toUseMeso = true;
      idMeso = geral.MESORREGIOES[i].ID;
      valueMeso = geral.MESORREGIOES[i].VALORES[indexAtributo];

      break;
    }
  }


  if (!toUseMeso) {

    for (j in geral.MICRORREGIOES) {
      if (geral.MICRORREGIOES[j].NOME_MICRORREGIAO === localizacao) {

        idMicro = geral.MICRORREGIOES[j].ID;
        valueMicro = geral.MICRORREGIOES[j].VALORES[indexAtributo];

        break;
      }

    }
  }

  if (toUseMeso) {
    if (valueMeso == 0) {

      labels.push(localizacao);
      values.push(valueMeso);

      var data = [{
        type: "sunburst",
        labels: labels,
        parents: parents,
        values: values,
        outsidetextfont: { size: 20, color: "#377eb8" },
        leaf: { opacity: 0.4 },
        marker: { line: { width: 2 } },
      }];

      Plotly.newPlot('myDiv', data, layout);
      return;

    }
  }
  else {
    if (valueMicro == 0) {

      labels.push(localizacao);
      values.push(valueMicro);

      var data = [{
        type: "sunburst",
        labels: labels,
        parents: parents,
        values: values,
        outsidetextfont: { size: 20, color: "#377eb8" },
        leaf: { opacity: 0.4 },
        marker: { line: { width: 2 } },
      }];

      Plotly.newPlot('myDiv', data, layout);
      return;
    }
  }

  if (!toUseMeso) {

    labels.push(localizacao);
    values.push(valueMicro);

    console.log(idMicro);

    for (m in geral.MUNICIPIOS) {
      if (geral.MUNICIPIOS[m].ID_MICRO == idMicro) {
        if (geral.MUNICIPIOS[m].VALORES[indexAtributo] != 0) {

          if(geral.MUNICIPIOS[m].NOME_MUNICIPIO === localizacao)
          {
            labels.push(geral.MUNICIPIOS[m].NOME_MUNICIPIO + " ");
          }
          else
          {
            labels.push(geral.MUNICIPIOS[m].NOME_MUNICIPIO);
          }
          parents.push(localizacao);
          values.push(geral.MUNICIPIOS[m].VALORES[indexAtributo]);

        }
      }
    }
  }

  var data = [{
    type: "sunburst",
    labels: labels,
    parents: parents,
    values: values,
    outsidetextfont: { size: 20, color: "#377eb8" },
    leaf: { opacity: 0.4 },
    marker: { line: { width: 2 } },
  }];

  Plotly.newPlot('myDiv', data, layout);
}


/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/



function doughnut() {
  Plotly.d3.csv("https://raw.githubusercontent.com/plotly/datasets/master/finance-charts-apple.csv", function (err, rows) {

    function unpack(rows, key) {
      return rows.map(function (row) { return row[key]; });
    }


    var trace1 = {
      type: "scatter",
      mode: "lines",
      name: 'AAPL High',
      x: unpack(rows, 'Date'),
      y: unpack(rows, 'AAPL.High'),
      line: { color: '#cf2317' }
    }

    var trace2 = {
      type: "scatter",
      mode: "lines",
      name: 'AAPL Low',
      x: unpack(rows, 'Date'),
      y: unpack(rows, 'AAPL.Low'),
      line: { color: '#7f7f7f' }
    }

    var data = [trace1, trace2];

    var layout = {
      title: 'Time Series with Rangeslider',
      xaxis: {
        autorange: true,
        range: ['2015-02-17', '2017-02-16'],
        rangeselector: {
          buttons: [
            {
              count: 1,
              label: '1m',
              step: 'month',
              stepmode: 'backward'
            },
            {
              count: 6,
              label: '6m',
              step: 'month',
              stepmode: 'backward'
            },
            { step: 'all' }
          ]
        },
        rangeslider: { range: ['2015-02-17', '2017-02-16'] },
        type: 'date'
      },
      yaxis: {
        autorange: true,
        range: [86.8700008333, 138.870004167],
        type: 'linear'
      }
    };

    Plotly.newPlot('myDiv', data, layout);
  })
}

/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/

function combo() {
  Plotly.d3.csv("https://raw.githubusercontent.com/plotly/datasets/master/finance-charts-apple.csv", function (err, rows) {

    function unpack(rows, key) {
      return rows.map(function (row) { return row[key]; });
    }


    var trace1 = {
      type: "scatter",
      mode: "lines",
      name: 'AAPL High',
      x: ["jan", "fev", "mar", "abr", "mai"],//unpack(rows, 'Date'),
      y: [10, 35, 11, 16, 7, 66],//unpack(rows, 'AAPL.High'),
      line: { color: '#f50f02' }
    }
    var data = [trace1];

    var layout = {
      title: 'Basic Time Series',
    };

    Plotly.newPlot('myDiv', data, layout);
  })
}

/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/

function pie() {
  var data = [{
    type: "sunburst",
    ids: [
      "North America", "Europe", "Australia", "North America - Football", "Soccer",
      "North America - Rugby", "Europe - Football", "Rugby",
      "Europe - American Football", "Australia - Football", "Association",
      "Australian Rules", "Autstralia - American Football", "Australia - Rugby",
      "Rugby League", "Rugby Union"
    ],
    labels: [
      "North<br>America", "Europe", "Australia", "Football", "Soccer", "Rugby",
      "Football", "Rugby", "American<br>Football", "Football", "Association",
      "Australian<br>Rules", "American<br>Football", "Rugby", "Rugby<br>League",
      "Rugby<br>Union"
    ],
    parents: [
      "", "", "", "North America", "North America", "North America", "Europe",
      "Europe", "Europe", "Australia", "Australia - Football", "Australia - Football",
      "Australia - Football", "Australia - Football", "Australia - Rugby",
      "Australia - Rugby"
    ],
    outsidetextfont: { size: 20, color: "#377eb8" },
    // leaf: {opacity: 0.4},
    marker: { line: { width: 2 } },
  }];

  var layout = {
    margin: { l: 0, r: 0, b: 0, t: 0 },
    sunburstcolorway: ["#636efa", "#ef553b", "#00cc96"],
  };


  Plotly.newPlot('myDiv', data, layout);
}


/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/


function plastic() {
  var trace1 = {
    x: [1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012],
    y: [219, 146, 112, 127, 124, 180, 236, 207, 236, 263, 350, 430, 474, 526, 488, 537, 500, 439],
    name: 'Rest of world',
    marker: { color: 'rgb(55, 83, 109)' },
    type: 'bar'
  };

  var trace2 = {
    x: [1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012],
    y: [16, 13, 10, 11, 28, 37, 43, 55, 56, 88, 105, 156, 270, 299, 340, 403, 549, 499],
    name: 'China',
    marker: { color: 'rgb(26, 118, 255)' },
    type: 'bar'
  };

  var data = [trace1, trace2];

  var layout = {
    title: 'US Export of Plastic Scrap',
    xaxis: {
      tickfont: {
        size: 14,
        color: 'rgb(107, 107, 107)'
      }
    },
    yaxis: {
      title: 'USD (millions)',
      titlefont: {
        size: 16,
        color: 'rgb(107, 107, 107)'
      },
      tickfont: {
        size: 14,
        color: 'rgb(107, 107, 107)'
      }
    },
    legend: {
      x: 0,
      y: 1.0,
      bgcolor: 'rgba(255, 255, 255, 0)',
      bordercolor: 'rgba(255, 255, 255, 0)'
    },
    barmode: 'group',
    bargap: 0.15,
    bargroupgap: 0.1
  };

  Plotly.newPlot('myDiv', data, layout);
}