//Type
function buildData(name) {
    this.type = 'scatterpolar'; //Fixo
    this.r = new Array();
    this.theta = new Array();
    this.fill = 'toself'; //Fixo
    this.name = name;
    this.showlegend = true;
    this.hoverlabel = new hoverlabel();
}

function hoverlabel() {
    this.font = new font();
    this.namelength = -1;
    this.align = "left";
}

function font() {
    this.size = 13;
}

function layout() {
    layout = {
        polar: {
            radialaxis: {
                autorange: true,
                visible: true,
            }
        }
    }
}

var oldMeso = "";
var oldMicro = "";
var oldMuni = "";
var alreadyPloted = false;

//O plot utilizando ano deixava o gráfico bugado 
var alfabeto = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];

function radarOnMicro(localization) {

    if (localization === oldMicro) {
        return;
    }

    radarAtributosMicro(localization)

    oldMicro = localization;

    var periodos = new Array();

    for (i in config.PERIODOS) {
        periodos.push(alfabeto[i]);

        if (i == config.PERIODOS.length - 1) {
            periodos.push(alfabeto[0]); //Ultimo valor deve ser repetido para fechar a forma do radar
        }
    }

    var arrayData = new Array();

    for (micro in jsonEstadoEvolucao.MICRORREGIOES) //Para toda as mesos
    {
        if (jsonEstadoEvolucao.MICRORREGIOES[micro].NOME_MICRORREGIAO === localization) {

            var currentDate = new buildData(jsonEstadoEvolucao.MICRORREGIOES[micro].NOME_MICRORREGIAO);

            for (currentValue in jsonEstadoEvolucao.MICRORREGIOES[micro].ATRIBUTOS[indexAtributo].VALORES) {

                currentDate.r.push(jsonEstadoEvolucao.MICRORREGIOES[micro].ATRIBUTOS[indexAtributo].VALORES[currentValue]);

                if (currentValue == jsonEstadoEvolucao.MICRORREGIOES[micro].ATRIBUTOS[indexAtributo].VALORES.length - 1) {
                    currentDate.r.push(jsonEstadoEvolucao.MICRORREGIOES[micro].ATRIBUTOS[indexAtributo].VALORES[0]);
                }

            }

            currentDate.theta = periodos;

            arrayData.push(currentDate);

            break;
        }
    }

    layout = {
        polar: {
            radialaxis: {
                autorange: true,
                visible: true,
            }
        }
    }

    Plotly.plot("myDiv2", arrayData, layout);

}

function radarOnMeso(localization) {

    if (localization === oldMeso) {
        return;
    }

    oldMeso = localization;

    radarAtributosMeso(localization);

    var periodos = new Array();

    for (i in config.PERIODOS) {
        periodos.push(alfabeto[i]);

        if (i == config.PERIODOS.length - 1) {
            periodos.push(alfabeto[0]); //Ultimo valor deve ser repetido para fechar a forma do radar
        }
    }

    var arrayData = new Array();

    for (meso in jsonEstadoEvolucao.MESORREGIOES) //Para toda as mesos
    {
        if (jsonEstadoEvolucao.MESORREGIOES[meso].NOME_MESORREGIAO === localization) {

            var currentDate = new buildData(jsonEstadoEvolucao.MESORREGIOES[meso].NOME_MESORREGIAO);

            for (currentValue in jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES) {

                currentDate.r.push(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES[currentValue]);

                if (currentValue == jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES.length - 1) {
                    currentDate.r.push(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES[0]);
                }

            }

            currentDate.theta = periodos;

            arrayData.push(currentDate);

            break;
        }
    }

    layout = {
        polar: {
            radialaxis: {
                autorange: true,
                visible: true,
            }
        }
    }

    Plotly.plot("myDiv2", arrayData, layout);

}

function radarOnMuni(localization) {

    if (localization === oldMuni) {
        return;
    }

    radarAtributosMuni(localization);

    oldMuni = localization;

    var periodos = new Array();

    for (i in config.PERIODOS) {
        periodos.push(alfabeto[i]);

        if (i == config.PERIODOS.length - 1) {
            periodos.push(alfabeto[0]); //Ultimo valor deve ser repetido para fechar a forma do radar
        }
    }

    var arrayData = new Array();

    for (meso in jsonEstadoEvolucao.MUNICIPIOS) //Para toda as mesos
    {
        if (jsonEstadoEvolucao.MUNICIPIOS[meso].NOME_MUNICIPIO === localization) {

            var currentDate = new buildData(jsonEstadoEvolucao.MUNICIPIOS[meso].NOME_MUNICIPIO + " ");

            for (currentValue in jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS[indexAtributo].VALORES) {

                currentDate.r.push(jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS[indexAtributo].VALORES[currentValue]);

                if (currentValue == jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS[indexAtributo].VALORES.length - 1) {
                    currentDate.r.push(jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS[indexAtributo].VALORES[0]);
                }

            }

            currentDate.theta = periodos;

            arrayData.push(currentDate);

            break;
        }
    }

    layout = {
        polar: {
            radialaxis: {
                autorange: true,
                visible: true,
            }
        }
    }

    Plotly.plot("myDiv2", arrayData, layout);

}

function radarOnAllMeso() {

    var periodos = new Array();

    for (i in config.PERIODOS) {
        periodos.push(alfabeto[i]);

        if (i == config.PERIODOS.length - 1) {
            periodos.push(alfabeto[0]); //Ultimo valor deve ser repetido para fechar a forma do radar
        }
    }

    var arrayData = new Array();

    for (meso in jsonEstadoEvolucao.MESORREGIOES) //Para toda as mesos
    {
        var currentDate = new buildData(jsonEstadoEvolucao.MESORREGIOES[meso].NOME_MESORREGIAO);

        for (currentValue in jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES) {

            currentDate.r.push(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES[currentValue]);

            if (currentValue == jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES.length - 1) {
                currentDate.r.push(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES[0]);
            }

        }

        currentDate.theta = periodos;

        arrayData.push(currentDate);
    }

    layout = {
        polar: {
            radialaxis: {
                autorange: true,
                visible: true,
            }
        }
    }

    Plotly.plot("myDiv2", arrayData, layout);

}

function radarAtributosMeso(localization) {

    if(alreadyPloted)
    {
        return;
    }

    alreadyPloted = true;

    var periodos = new Array();

    for (i in config.PERIODOS) {
        periodos.push(alfabeto[i]);

        if (i == config.PERIODOS.length - 1) {
            periodos.push(alfabeto[0]); //Ultimo valor deve ser repetido para fechar a forma do radar
        }
    }

    var arrayData = new Array();

    for (meso in jsonEstadoEvolucao.MESORREGIOES) //Para toda as mesos
    {
        if (jsonEstadoEvolucao.MESORREGIOES[meso].NOME_MESORREGIAO === localization) {

            for (currentIndex in jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS) {

                var currentDate = new buildData(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[currentIndex].NOME);

                for (currentValue in jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[currentIndex].VALORES) {

                    currentDate.r.push(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[currentIndex].VALORES[currentValue]);

                    if (currentValue == jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[currentIndex].VALORES.length - 1) {
                        currentDate.r.push(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[currentIndex].VALORES[0]);
                    }

                }

                currentDate.theta = periodos;

                arrayData.push(currentDate);
            }


            break;
        }
    }

    layout = {
        polar: {
            radialaxis: {
                autorange: true,
                visible: true,
            }
        }
    }

    Plotly.plot("myDiv3", arrayData, layout);
}


function radarAtributosMicro(localization) {

    if(alreadyPloted)
    {
        return;
    }

    alreadyPloted = true;

    var periodos = new Array();

    for (i in config.PERIODOS) {
        periodos.push(alfabeto[i]);

        if (i == config.PERIODOS.length - 1) {
            periodos.push(alfabeto[0]); //Ultimo valor deve ser repetido para fechar a forma do radar
        }
    }

    var arrayData = new Array();

    for (meso in jsonEstadoEvolucao.MICRORREGIOES) //Para toda as mesos
    {
        if (jsonEstadoEvolucao.MICRORREGIOES[meso].NOME_MICRORREGIAO === localization) {

            for (currentIndex in jsonEstadoEvolucao.MICRORREGIOES[meso].ATRIBUTOS) {

                var currentDate = new buildData(jsonEstadoEvolucao.MICRORREGIOES[meso].ATRIBUTOS[currentIndex].NOME);

                for (currentValue in jsonEstadoEvolucao.MICRORREGIOES[meso].ATRIBUTOS[currentIndex].VALORES) {

                    currentDate.r.push(jsonEstadoEvolucao.MICRORREGIOES[meso].ATRIBUTOS[currentIndex].VALORES[currentValue]);

                    if (currentValue == jsonEstadoEvolucao.MICRORREGIOES[meso].ATRIBUTOS[currentIndex].VALORES.length - 1) {
                        currentDate.r.push(jsonEstadoEvolucao.MICRORREGIOES[meso].ATRIBUTOS[currentIndex].VALORES[0]);
                    }

                }

                currentDate.theta = periodos;

                arrayData.push(currentDate);
            }


            break;
        }
    }

    layout = {
        polar: {
            radialaxis: {
                autorange: true,
                visible: true,
            }
        }
    }

    Plotly.plot("myDiv3", arrayData, layout);
}

function radarAtributosMuni(localization) {

    if(alreadyPloted)
    {
        return;
    }

    alreadyPloted = true;

    var periodos = new Array();

    for (i in config.PERIODOS) {
        periodos.push(alfabeto[i]);

        if (i == config.PERIODOS.length - 1) {
            periodos.push(alfabeto[0]); //Ultimo valor deve ser repetido para fechar a forma do radar
        }
    }

    var arrayData = new Array();

    for (meso in jsonEstadoEvolucao.MUNICIPIOS) //Para toda as mesos
    {
        if (jsonEstadoEvolucao.MUNICIPIOS[meso].NOME_MUNICIPIO === localization) {

            for (currentIndex in jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS) {

                var currentDate = new buildData(jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS[currentIndex].NOME);

                for (currentValue in jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS[currentIndex].VALORES) {

                    currentDate.r.push(jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS[currentIndex].VALORES[currentValue]);

                    if (currentValue == jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS[currentIndex].VALORES.length - 1) {
                        currentDate.r.push(jsonEstadoEvolucao.MUNICIPIOS[meso].ATRIBUTOS[currentIndex].VALORES[0]);
                    }

                }

                currentDate.theta = periodos;

                arrayData.push(currentDate);
            }


            break;
        }
    }

    layout = {
        polar: {
            radialaxis: {
                autorange: true,
                visible: true,
            }
        }
    }

    Plotly.plot("myDiv3", arrayData, layout);
}
