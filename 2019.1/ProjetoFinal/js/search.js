async function filterFunction(categoria, meso, micro, valorDe, valorAte) {

    //Todos os campos vazios lul
    if (categoria === '' && meso === '' && micro === '' && !valorDe && !valorAte) {
        clearSearchOnMap();
        return;
    }

    var regexCategoria = null;
    var regexMeso = null;
    var regexMicro = null;
    var regexValorDe = valorDe;
    var regexValorAte = valorAte;

    if (categoria != '') {
        regexCategoria = new RegExp(categoria, "i");
    }

    if (meso != '') {
        regexMeso = new RegExp(meso, "i");
    }

    if (micro != '') {
        regexMicro = new RegExp(micro, "i");
    }

    /*if (!valorDe) {
        regexValorDe = valorDe;
    }

    if (!valorAte) {
        regexValorAte = valorAte;
    }*/

    var output = [];
    var outputIndex = 0;

    var valorMax = jsonEstadoGeral.MAX_Valores[indexAtributo];
    var valorMin = jsonEstadoGeral.MIN_Valores[indexAtributo];

    /*console.log(regexCategoria);
    console.log(regexMeso);
    console.log(regexMicro);
    console.log(regexValorDe);
    console.log(regexValorAte);*/

    /* jsonEstadoGeral é inicializado em mapa.js ; Json jsonEstadoGeral carregado com preload*/

    /*var toAddMeso = false;

    for (i in jsonEstadoGeral.MESORREGIOES) {
        toAddMeso = false;

        if (regexCategoria != null) {
            for (c in jsonEstadoGeral.MESORREGIOES[i].CATEGORIA) {
                if (jsonEstadoGeral.MESORREGIOES[i].CATEGORIA[c].search(regexCategoria) != -1) {
                    toAddMeso = true;
                    break;
                }
            }

            if (!toAddMeso) {
                continue;
            }
        }

        if (regexMeso != null) {
            if (jsonEstadoGeral.MESORREGIOES[i].ID.toString().search(regexMeso) != -1) {
                toAddMeso = true;
            }
            else {
                toAddMeso = false;
            }

            if (!toAddMeso) {
                continue;
            }
        }

        if (regexValorDe != null && regexValorAte != null) {
            if (jsonEstadoGeral.MESORREGIOES[i].VALOR > regexValorDe && jsonEstadoGeral.MESORREGIOES[i].VALOR < regexValorAte) {
                toAddMeso = true;
            }
            else {
                toAddMeso = false;
            }

            if (!toAddMeso) {
                continue;
            }
        }

        if (toAddMeso) {
            output[outputIndex] = jsonEstadoGeral.MESORREGIOES[i];
            outputIndex++;
        }

    }

    var toAddMicro = false;

    for (i in jsonEstadoGeral.MICRORREGIOES) {
        toAddMicro = false;

        if (regexCategoria != null) {
            for (c in jsonEstadoGeral.MICRORREGIOES[i].CATEGORIA) {
                if (jsonEstadoGeral.MICRORREGIOES[i].CATEGORIA[c].search(regexCategoria) != -1) {
                    toAddMicro = true;
                    break;
                }
            }

            if (!toAddMicro) {
                continue;
            }
        }

        if (regexMicro != null) {
            if (jsonEstadoGeral.MICRORREGIOES[i].ID.toString().search(regexMicro) != -1) {
                toAddMicro = true;
            }
            else {
                toAddMicro = false;
            }

            if (!toAddMicro) {
                continue;
            }
        }

        if (regexValorDe != null && regexValorAte != null) {
            if (jsonEstadoGeral.MICRORREGIOES[i].VALOR > regexValorDe && jsonEstadoGeral.MICRORREGIOES[i].VALOR < regexValorAte) {
                toAddMicro = true;
            }
            else {
                toAddMicro = false;
            }

            if (!toAddMicro) {
                continue;
            }
        }

        if (toAddMicro) {
            output[outputIndex] = jsonEstadoGeral.MICRORREGIOES[i];
            outputIndex++;
        }

    }*/

    var toAddMunicipios = false;

    /* Municipios*/
    for (i in jsonEstadoGeral.MUNICIPIOS) {
        toAddMunicipios = false;
        if (regexCategoria != null) {
            for (c in jsonEstadoGeral.MUNICIPIOS[i].CATEGORIA) {
                if (jsonEstadoGeral.MUNICIPIOS[i].CATEGORIA[c].search(regexCategoria) != -1) {
                    toAddMunicipios = true;
                    break;
                }
            }

            if (!toAddMunicipios) {
                continue;
            }
        }

        if (regexMeso != null) {
            if (jsonEstadoGeral.MUNICIPIOS[i].ID_MESO.toString().search(regexMeso) != -1) {
                toAddMunicipios = true;
            }
            else {
                toAddMunicipios = false;
            }

            if (!toAddMunicipios) {
                continue;
            }
        }

        if (regexMicro != null) {
            if (jsonEstadoGeral.MUNICIPIOS[i].ID_MICRO.toString().search(regexMicro) != -1) {
                toAddMunicipios = true;
            }
            else {
                toAddMunicipios = false;
            }

            if (!toAddMunicipios) {
                continue;
            }
        }

        if (regexValorDe != null && regexValorAte != null) {
            if (jsonEstadoGeral.MUNICIPIOS[i].VALORES[indexAtributo] > regexValorDe && jsonEstadoGeral.MUNICIPIOS[i].VALORES[indexAtributo] < regexValorAte) {
                toAddMunicipios = true;
            }
            else {
                toAddMunicipios = false;
            }

            if (!toAddMunicipios) {
                continue;
            }
        }
        else if (regexValorDe != null) {
            if (jsonEstadoGeral.MUNICIPIOS[i].VALORES[indexAtributo] > regexValorDe && jsonEstadoGeral.MUNICIPIOS[i].VALORES[indexAtributo] < valorMax) {
                toAddMunicipios = true;
            }
            else {
                toAddMunicipios = false;
            }

            if (!toAddMunicipios) {
                continue;
            }
        }
        else if (regexValorAte != null) { //Desnecessauro ; Mas apenas para facilitar leitura
            if (jsonEstadoGeral.MUNICIPIOS[i].VALORES[indexAtributo] > valorMin && jsonEstadoGeral.MUNICIPIOS[i].VALORES[indexAtributo] < regexValorAte) {
                toAddMunicipios = true;
            }
            else {
                toAddMunicipios = false;
            }

            if (!toAddMunicipios) {
                continue;
            }
        }

        if (toAddMunicipios) {
            output[outputIndex] = jsonEstadoGeral.MUNICIPIOS[i]; //Copia de referencia
            outputIndex++;
        }

    }


    //for (i in output)
    //{
    //    console.log(output[i]);
    //}
    clearSearchOnMap();
    toColorBySearch(output); //

}

