let visoes_disponiveis = ["TreeMap", "BubbleChart", "Sunburst", "ZoomableSunburst"];

function atualizarVisoes() {
    visoes_disponiveis.forEach(item => {
        element_id = "div_" + item;
        function_name = "atualiza" + item;
        try {
            window[function_name](element_id);
        } catch (e) {
           console.error("Não foi possível chamar o elemento: " + item);
        }
    });
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
    showlegend: true
  };
  Plotly.newPlot('myDiv', data,layout);
}

(function() {
    // your page initialization code here
    // the DOM will be available here
    setTimeout(atualizarVisoes, 0);
})();
