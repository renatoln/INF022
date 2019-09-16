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

/* Localizacao = meso ou micro */
/* Plota todos os municipios pela localizacao */
/* Se a localização passada for a meso, plota os municipios agrupando por micro */
/* Se micro, plota os municipios */
/* Não faz sentido plotar isso aqui por municipios, uma vez que não existe agrupamento nesse nivel */
/* Valores iguais a 0 não são plotados, uma vez que poluem o gráfico (o tamanho da forma depende do valor) */
/* Tamanho 0 implica sem forma, o que faz com que as informações se agrupem em algum canto do gráfico */
/* Seria interessante fazer uma notificação para utilizarmos nessas situações */
function sunburst(localizacao) {

  if (!localizacao) {
    return;
  }

  var layout = {
    margin: { l: 0, r: 0, b: 0, t: 0 },
    width: 500,
    height: 500,
    sunburstcolorway: [
      "#636efa", "#EF553B", "#00cc96", "#ab63fa", "#19d3f3",
      "#e763fa", "#FECB52", "#FFA15A", "#FF6692", "#B6E880"
    ],
    extendsunburstcolorway: true
  };

  var toUseMeso = false;

  var idMeso = 0;
  var valueMeso = 0;

  var idMicro = 0;
  var valueMicro = 0;

  var labels = new Array();
  var parents = new Array();
  var values = new Array();

  parents.push(""); //O primeiro nó (Root) é vazio, pois aqui começa o agrupamento

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

    for (mun in geral.MUNICIPIOS) {
      if (geral.MUNICIPIOS[mun].ID_MICRO == idMicro) {
        if (geral.MUNICIPIOS[mun].VALORES[indexAtributo] != 0) {

          if (geral.MUNICIPIOS[mun].NOME_MUNICIPIO === localizacao) {
            labels.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO + " ");
          }
          else {
            labels.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO);
          }
          parents.push(localizacao);
          values.push(geral.MUNICIPIOS[mun].VALORES[indexAtributo]);

        }
      }
    }
  }
  else {
    labels.push(localizacao);
    values.push(valueMeso);

    for (micro in geral.MICRORREGIOES) {
      if (geral.MICRORREGIOES[micro].ID_MESO == idMeso) {

        labels.push(geral.MICRORREGIOES[micro].NOME_MICRORREGIAO);
        values.push(geral.MICRORREGIOES[micro].VALORES[indexAtributo]);
        parents.push(localizacao);

        for (mun in geral.MUNICIPIOS) {
          if (geral.MUNICIPIOS[mun].ID_MICRO == geral.MICRORREGIOES[micro].ID) {

            if (geral.MUNICIPIOS[mun].VALORES[indexAtributo] != 0) {

              if (geral.MUNICIPIOS[mun].NOME_MUNICIPIO === geral.MICRORREGIOES[micro].NOME_MICRORREGIAO) {
                labels.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO + " ");
              }
              else {
                labels.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO);
              }
              parents.push(geral.MICRORREGIOES[micro].NOME_MICRORREGIAO);
              values.push(geral.MUNICIPIOS[mun].VALORES[indexAtributo]);

            }
          }
        }
      }
    }
  }

  /* Poderia existir uma função mais inteligente para realizar o ajuste no tamanho da fonte */

  if (localizacao.length > 25) {
    var data = [{
      type: "sunburst",
      labels: labels,
      parents: parents,
      values: values,
      outsidetextfont: { size: 10, color: "#377eb8" },
      leaf: { opacity: 0.4 },
      marker: { line: { width: 2 } },
      branchvalues: 'total'
    }];
  }
  else if (localizacao.length > 20) {
    var data = [{
      type: "sunburst",
      labels: labels,
      parents: parents,
      values: values,
      outsidetextfont: { size: 12, color: "#377eb8" },
      leaf: { opacity: 0.4 },
      marker: { line: { width: 2 } },
      branchvalues: 'total'
    }];
  }
  else {
    var data = [{
      type: "sunburst",
      labels: labels,
      parents: parents,
      values: values,
      outsidetextfont: { size: 14, color: "#377eb8" },
      leaf: { opacity: 0.4 },
      marker: { line: { width: 2 } },
      branchvalues: 'total'
    }];
  }

  //branchvalues: 'total' -> Note that this means that the sum of the values of the children 
  //cannot exceed the value of their parent when branchvalues "total".
  //When branchvalues "relative" (the default), children will not take up all of 
  //the space below their parent (unless the parent is the root and it has a value of 0).

  Plotly.newPlot('myDiv', data, layout);
}

function sunburstAll() {

  var layout = {
    margin: { l: 0, r: 0, b: 0, t: 0 },
    width: 500,
    height: 500,
    sunburstcolorway: [
      "#636efa", "#EF553B", "#00cc96", "#ab63fa", "#19d3f3",
      "#e763fa", "#FECB52", "#FFA15A", "#FF6692", "#B6E880"
    ],
    extendsunburstcolorway: true
  };

  var idMeso = 0;
  var valueTotal = 0;

  var labels = new Array();
  var parents = new Array();
  var values = new Array();

  parents.push(""); //O primeiro nó (Root) é vazio, pois aqui começa o agrupamento

  for (meso in geral.MESORREGIOES) {
    valueTotal += geral.MESORREGIOES[meso].VALORES[indexAtributo]; //Soma total do atributo atual
  }

  labels.push(config.ESTADO);
  values.push(valueTotal);

  for (meso in geral.MESORREGIOES) {

    if (geral.MESORREGIOES[meso].VALORES[indexAtributo] == 0) {
      continue;
    }

    idMeso = geral.MESORREGIOES[meso].ID;
    labels.push(geral.MESORREGIOES[meso].NOME_MESORREGIAO);
    values.push(geral.MESORREGIOES[meso].VALORES[indexAtributo]);
    parents.push(config.ESTADO);

    for (micro in geral.MICRORREGIOES) {
      if (geral.MICRORREGIOES[micro].ID_MESO == idMeso) {

        labels.push(geral.MICRORREGIOES[micro].NOME_MICRORREGIAO);
        values.push(geral.MICRORREGIOES[micro].VALORES[indexAtributo]);
        parents.push(geral.MESORREGIOES[meso].NOME_MESORREGIAO);

        for (mun in geral.MUNICIPIOS) {
          if (geral.MUNICIPIOS[mun].ID_MICRO == geral.MICRORREGIOES[micro].ID) {

            if (geral.MUNICIPIOS[mun].VALORES[indexAtributo] != 0) {

              if (geral.MUNICIPIOS[mun].NOME_MUNICIPIO === geral.MICRORREGIOES[micro].NOME_MICRORREGIAO) {
                labels.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO + " ");
              }
              else {
                labels.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO);
              }
              parents.push(geral.MICRORREGIOES[micro].NOME_MICRORREGIAO);
              values.push(geral.MUNICIPIOS[mun].VALORES[indexAtributo]);

            }
          }
        }
      }
    }
  }

  /* Poderia existir uma função mais inteligente para realizar o ajuste no tamanho da fonte */

  var data = [{
    type: "sunburst",
    labels: labels,
    parents: parents,
    values: values,
    outsidetextfont: { size: 14, color: "#377eb8" },
    leaf: { opacity: 0.4 },
    marker: { line: { width: 2 } },
    branchvalues: 'total'
  }];

  //branchvalues: 'total' -> Note that this means that the sum of the values of the children 
  //cannot exceed the value of their parent when branchvalues "total".
  //When branchvalues "relative" (the default), children will not take up all of 
  //the space below their parent (unless the parent is the root and it has a value of 0).

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