/* Localizacao = meso ou micro */
/* Plota todos os municipios pela localizacao */
/* Se a localização passada for a meso, plota os municipios agrupando por micro */
/* Se micro, plota os municipios */
/* Não faz sentido plotar isso aqui por municipios, uma vez que não existe agrupamento nesse nivel */
/* Valores iguais a 0 não são plotados, uma vez que poluem o gráfico (o tamanho da forma depende do valor) */
/* Tamanho 0 implica sem forma, o que faz com que as informações se agrupem em algum canto do gráfico */
/* Seria interessante fazer uma notificação para utilizarmos nessas situações */

/*
Putting the async keyword before a function makes it an asynchronous function. This basically does 2 things to the function:
1 - If a function doesn't return a promise the JS engine will wrap this value into a resolved promise. Thus, the function will always return a promise.
*/

var oldLocalizacao = "";

var labelsLocation = new Array();
var parentsLocation = new Array();
var valuesLocation = new Array();

async function sunburst(localizacao) {

    if (!localizacao) {
        return;
    }

    if (localizacao === oldLocalizacao) {
        return;
    }

    oldLocalizacao = localizacao;

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

    var labelsLocation = null;
    var parentsLocation = null;
    var valuesLocation = null; //Fastest way to clean an array hehe

    var labelsLocation = new Array();
    var parentsLocation = new Array();
    var valuesLocation = new Array();

    parentsLocation.push(""); //O primeiro nó (Root) é vazio, pois aqui começa o agrupamento

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

            labelsLocation.push(localizacao);
            valuesLocation.push(valueMeso);

            var data = [{
                type: "sunburst",
                labels: labelsLocation,
                parents: parentsLocation,
                values: valuesLocation,
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

            labelsLocation.push(localizacao);
            valuesLocation.push(valueMicro);

            var data = [{
                type: "sunburst",
                labels: labelsLocation,
                parents: parentsLocation,
                values: valuesLocation,
                outsidetextfont: { size: 20, color: "#377eb8" },
                leaf: { opacity: 0.4 },
                marker: { line: { width: 2 } },
            }];

            Plotly.newPlot('myDiv', data, layout);
            return;
        }
    }

    if (!toUseMeso) {

        labelsLocation.push(localizacao);
        valuesLocation.push(valueMicro);

        for (mun in geral.MUNICIPIOS) {
            if (geral.MUNICIPIOS[mun].ID_MICRO == idMicro) {
                if (geral.MUNICIPIOS[mun].VALORES[indexAtributo] == 0) {
                    continue;
                }

                if (geral.MUNICIPIOS[mun].NOME_MUNICIPIO === localizacao) {
                    labelsLocation.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO + " ");
                }
                else {
                    labelsLocation.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO);
                }
                parentsLocation.push(localizacao);
                valuesLocation.push(geral.MUNICIPIOS[mun].VALORES[indexAtributo]);
            }
        }
    }
    else {
        labelsLocation.push(localizacao);
        valuesLocation.push(valueMeso);

        for (micro in geral.MICRORREGIOES) {
            if (geral.MICRORREGIOES[micro].ID_MESO == idMeso) {

                labelsLocation.push(geral.MICRORREGIOES[micro].NOME_MICRORREGIAO);
                valuesLocation.push(geral.MICRORREGIOES[micro].VALORES[indexAtributo]);
                parentsLocation.push(localizacao);

                for (mun in geral.MUNICIPIOS) {
                    if (geral.MUNICIPIOS[mun].ID_MICRO == geral.MICRORREGIOES[micro].ID) {

                        if (geral.MUNICIPIOS[mun].VALORES[indexAtributo] == 0) {
                            continue;
                        }

                        if (geral.MUNICIPIOS[mun].NOME_MUNICIPIO === geral.MICRORREGIOES[micro].NOME_MICRORREGIAO) {
                            labelsLocation.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO + " ");
                        }
                        else {
                            labelsLocation.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO);
                        }
                        parentsLocation.push(geral.MICRORREGIOES[micro].NOME_MICRORREGIAO);
                        valuesLocation.push(geral.MUNICIPIOS[mun].VALORES[indexAtributo]);
                    }
                }
            }
        }
    }

    /* Poderia existir uma função mais inteligente para realizar o ajuste no tamanho da fonte */

    if (localizacao.length > 25) {
        var data = [{
            type: "sunburst",
            labels: labelsLocation,
            parents: parentsLocation,
            values: valuesLocation,
            outsidetextfont: { size: 10, color: "#377eb8" },
            leaf: { opacity: 0.4 },
            marker: { line: { width: 2 } },
            branchvalues: 'total'
        }];
    }
    else if (localizacao.length > 20) {
        var data = [{
            type: "sunburst",
            labels: labelsLocation,
            parents: parentsLocation,
            values: valuesLocation,
            outsidetextfont: { size: 12, color: "#377eb8" },
            leaf: { opacity: 0.4 },
            marker: { line: { width: 2 } },
            branchvalues: 'total'
        }];
    }
    else {
        var data = [{
            type: "sunburst",
            labels: labelsLocation,
            parents: parentsLocation,
            values: valuesLocation,
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

var labelsGlobal = new Array();
var parentsGlobal = new Array();
var valuesGlobal = new Array();
var oldIndexAtributo = -1;

async function sunburstAll(div) {

    if (oldIndexAtributo == indexAtributo) {
        return;
    }

    oldIndexAtributo = indexAtributo;

    var layout = {
        margin: { l: 10, r: 10, b: 10, t: 50 },
        width: 450,
        height: 500,
        sunburstcolorway: [
            "#636efa", "#EF553B", "#00cc96", "#ab63fa", "#19d3f3",
            "#e763fa", "#FECB52", "#FFA15A", "#FF6692", "#B6E880"
        ],
        extendsunburstcolorway: true
    };

    var labelsGlobal = null;
    var parentsGlobal = null;
    var valuesGlobal = null;

    var labelsGlobal = new Array();
    var parentsGlobal = new Array();
    var valuesGlobal = new Array();

    var idMeso = 0;
    var valueTotal = 0;

    parentsGlobal.push(""); //O primeiro nó (Root) é vazio, pois aqui começa o agrupamento

    for (meso in geral.MESORREGIOES) {
        valueTotal += geral.MESORREGIOES[meso].VALORES[indexAtributo]; //Soma total do atributo atual
    }

    labelsGlobal.push(config.ESTADO);
    valuesGlobal.push(valueTotal);

    for (meso in geral.MESORREGIOES) {

        if (geral.MESORREGIOES[meso].VALORES[indexAtributo] == 0) {
            continue;
        }

        idMeso = geral.MESORREGIOES[meso].ID;
        labelsGlobal.push(geral.MESORREGIOES[meso].NOME_MESORREGIAO);
        valuesGlobal.push(geral.MESORREGIOES[meso].VALORES[indexAtributo]);
        parentsGlobal.push(config.ESTADO);

        for (micro in geral.MICRORREGIOES) {
            if (geral.MICRORREGIOES[micro].ID_MESO == idMeso) {

                labelsGlobal.push(geral.MICRORREGIOES[micro].NOME_MICRORREGIAO);
                valuesGlobal.push(geral.MICRORREGIOES[micro].VALORES[indexAtributo]);
                parentsGlobal.push(geral.MESORREGIOES[meso].NOME_MESORREGIAO);

                for (mun in geral.MUNICIPIOS) {
                    if (geral.MUNICIPIOS[mun].ID_MICRO == geral.MICRORREGIOES[micro].ID) {

                        if (geral.MUNICIPIOS[mun].VALORES[indexAtributo] == 0) {
                            continue;
                        }

                        if (geral.MUNICIPIOS[mun].NOME_MUNICIPIO === geral.MICRORREGIOES[micro].NOME_MICRORREGIAO) {
                            labelsGlobal.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO + " ");
                        }
                        else {
                            labelsGlobal.push(geral.MUNICIPIOS[mun].NOME_MUNICIPIO);
                        }
                        parentsGlobal.push(geral.MICRORREGIOES[micro].NOME_MICRORREGIAO);
                        valuesGlobal.push(geral.MUNICIPIOS[mun].VALORES[indexAtributo]);


                    }
                }
            }
        }
    }

    /* Poderia existir uma função mais inteligente para realizar o ajuste no tamanho da fonte */

    var data = [{
        type: "sunburst",
        labels: labelsGlobal,
        parents: parentsGlobal,
        values: valuesGlobal,
        outsidetextfont: { size: 14, color: "#377eb8" },
        leaf: { opacity: 0.4 },
        marker: { line: { width: 2 } },
        branchvalues: 'total'
    }];

    //branchvalues: 'total' -> Note that this means that the sum of the values of the children 
    //cannot exceed the value of their parent when branchvalues "total".
    //When branchvalues "relative" (the default), children will not take up all of 
    //the space below their parent (unless the parent is the root and it has a value of 0).
    if (div == null) {
        Plotly.newPlot("myDiv", data, layout);
    }
    else {
        Plotly.newPlot(div, data, layout);
    }
}