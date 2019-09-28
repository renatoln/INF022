//Type
function data(name) {
    this.type = 'scatterpolargl'; //Fixo
    this.r = new Array();
    this.theta = new Array();
    this.fill = 'none'; //Fixo
    this.name = name;
}

//O plot utilizando ano deixava o gráfico bugado 
var alfabeto = ["A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" , "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" , "U" , "V" , "W" , "X" , "Y" , "Z"];

//jsonEstadoEvolucao

/*    for (meso in geral.MESORREGIOES) {
        valueTotal += geral.MESORREGIOES[meso].VALORES[indexAtributo]; //Soma total do atributo atual
    }*/

function radarOnMeso() {

    var periodos = new Array();

    for(i in config.PERIODOS)
    {
        periodos.push(alfabeto[i]);
        
        if(i == config.PERIODOS.length - 1)
        {
            periodos.push(alfabeto[0]); //Ultimo valor deve ser repetido para fechar a forma do radar
        }
    }


    var arrayData = new Array();

    for(meso in jsonEstadoEvolucao.MESORREGIOES) //Para toda as mesos
    {
        var currentDate = new data(jsonEstadoEvolucao.MESORREGIOES[meso].NOME_MESORREGIAO);

        for(currentValue in jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES)
        {
            if(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES[currentValue] === 0)
            {
                continue; //Little otimização
            }

            currentDate.r.push(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES[currentValue]);

            if(currentValue == jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES.length - 1)
            {
                currentDate.r.push(jsonEstadoEvolucao.MESORREGIOES[meso].ATRIBUTOS[indexAtributo].VALORES[0]);
            }

        }

        currentDate.theta = periodos;

        arrayData.push(currentDate);
    }

    layout = {
        polar: {
            radialaxis: {
                autorange : true,
                visible: true,
            }
        }
    }

    console.log(layout);

    for(i in arrayData)
    {
        console.log(arrayData[i]);
    }

    Plotly.plot("radarChartTab", arrayData, layout);

}