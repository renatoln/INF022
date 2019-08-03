function searchFunction(urlJson, categoria, meso, micro, valorDe, valorAte) {
    var json;
    $(function () {
        $.ajax({
            url: urlJson,
            type: 'get',
            dataType: 'json',
            success: function (response) {
                console.log(response);
                json = response;
                filterFunction(json, categoria, meso, micro, valorDe, valorAte);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("erro -> " + XMLHttpRequest.status + ' ☺☻ ' +
                    XMLHttpRequest.statusText);
                return false;
            }
        });

    }); //Mover isso para outro canto pls ;~;

}

function filterFunction(json, categoria, meso, micro, valorDe, valorAte) {

    //Todos os campos vazios lul
    if (categoria === '' && meso === '' && micro === '' && !valorDe && !valorAte) {
        return;
    }

    var regexCategoria = null;
    var regexMeso = null;
    var regexMicro = null;
    var regexValorDe = null;
    var regexValorAte = null;

    if (categoria != '') {
        regexCategoria = new RegExp(categoria, "i");
    }

    if (meso != '') {
        regexMeso = new RegExp(meso, "i");
    }

    if (micro != '') {
        regexMicro = new RegExp(micro, "i");
    }

    if (!valorDe) {
        regexValorDe = valorDe;
    }

    if (!valorAte) {
        regexValorAte = valorAte;
    }

    var output = [];
    var outputIndex = 0;

    /*
    console.log(regexCategoria);
    console.log(regexMeso);
    console.log(regexMicro);
    console.log(regexValorDe);
    console.log(regexValorAte);
    */

    //;~; me ajude a me ajudar, soda
    //Isso aqui tem que virar funções Async lul

    var toAddMeso = false;

    /* Meso */
    for (i in json.MESORREGIOES) {
        toAddMeso = false;

        if (regexCategoria != null) { //TODO LOOP
            for (c in json.MESORREGIOES[i].CATEGORIA) {
                if (json.MESORREGIOES[i].CATEGORIA[c].search(regexCategoria) != -1) {
                    toAddMeso = true;
                    break;
                }
            }

            if (!toAddMeso) {
                continue;
            }
        }

        if (regexMeso != null) {
            if (json.MESORREGIOES[i].ID.toString().search(regexMeso) != -1) {
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
            if (json.MESORREGIOES[i].VALOR > regexValorDe && json.MESORREGIOES[i].VALOR < regexValorAte) {
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
            output[outputIndex] = json.MESORREGIOES[i]; // :think: isso é valido em js... lul
            outputIndex++;
        }

    }

    /* Micro */

    var toAddMicro = false;

    for (i in json.MICRORREGIOES) {
        toAddMicro = false;

        if (regexCategoria != null) {
            for (c in json.MICRORREGIOES[i].CATEGORIA) {
                if (json.MICRORREGIOES[i].CATEGORIA[c].search(regexCategoria) != -1) {
                    toAddMicro = true;
                    break;
                }
            }

            if (!toAddMicro) {
                continue;
            }
        }

        if (regexMicro != null) {
            if (json.MICRORREGIOES[i].ID.toString().search(regexMicro) != -1) {
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
            if (json.MICRORREGIOES[i].VALOR > regexValorDe && json.MICRORREGIOES[i].VALOR < regexValorAte) {
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
            output[outputIndex] = json.MICRORREGIOES[i]; // :think: isso é valido em js... lul
            outputIndex++;
        }

    }

    var toAddMunicipios = false;

    /* Municipios*/
    for (i in json.MUNICIPIOS) {
        toAddMunicipios = false;
        if (regexCategoria != null) { //TODO LOOP
            for (c in json.MUNICIPIOS[i].CATEGORIA) {
                if (json.MUNICIPIOS[i].CATEGORIA[c].search(regexCategoria) != -1) {
                    toAddMunicipios = true;
                    break;
                }
            }

            if (!toAddMunicipios) {
                continue;
            }
        }

        if (regexMeso != null) {
            if (json.MUNICIPIOS[i].ID_MESO.toString().search(regexMeso) != -1) {
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
            if (json.MUNICIPIOS[i].ID_MICRO.toString().search(regexMicro) != -1) {
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
            if (json.MUNICIPIOS[i].VALOR > regexValorDe && json.MUNICIPIOS[i].VALOR < regexValorAte) {
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
            output[outputIndex] = json.MUNICIPIOS[i]; // :think: isso é valido em js... lul
            outputIndex++;
        }

    }


    for (i in output)
        console.log(output[i]);


    return output;
}