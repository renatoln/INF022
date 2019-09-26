$.getScript("views/bubble.js");

function atualizarVisoes(){

}



function lineChart(periodo, valor, cidade) {
  var trace1 = {
    x: periodo,
    y: valor,
    name: getCurrentAttribute(),
    type: 'scatter'
  };

  var layout = {
    title: cidade,
    showlegend: true
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
    name: getCurrentAttribute(),
    type: 'bar'
  }];

  var layout = {
    title: cidade,
    showlegend: true
  };

  Plotly.newPlot('myDiv', data, layout);
}


/****************************************************************************************************************/
/****************************************************************************************************************/
/*****************************************Menu*******************************************************************/
/****************************************************************************************************************/

function attributeCompare(periodo, atributos, cidade) {
  let data = [];
  let line = {};

  for (let i of atributos) {
    line = {
      x: periodo,
      y: i.VALORES,
      name: i.NOME,
      type: 'scatter'
    };

    data.push(line);
  }

  let layout = {
    title: cidade,
    showlegend: false
  };
  Plotly.newPlot('myDiv', data);
}