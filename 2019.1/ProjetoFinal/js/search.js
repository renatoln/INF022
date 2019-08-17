
/*function preload(){
	$(function () {
        return $.ajax({
            url: "./jsons/MUNICIPIOS_JSON_TESTES.json",
            type: 'get',
            dataType: 'json',
            success: function (response) {
                testes = response;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("erro -> " + XMLHttpRequest.status + ' ☺☻ ' +
                    XMLHttpRequest.statusText);
                return false;
            }
        });

    }); 
}*/


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


    console.log(regexCategoria);
    console.log(regexMeso);
    console.log(regexMicro);
    console.log(regexValorDe);
    console.log(regexValorAte);

    /* Testes é inicializado em sketch.js ; Json geral carregado com preload*/

    var toAddMeso = false;

    /* Meso */
    for (i in testes.MESORREGIOES) {
        toAddMeso = false;

        if (regexCategoria != null) {
            for (c in testes.MESORREGIOES[i].CATEGORIA) {
                if (testes.MESORREGIOES[i].CATEGORIA[c].search(regexCategoria) != -1) {
                    toAddMeso = true;
                    break;
                }
            }

            if (!toAddMeso) {
                continue;
            }
        }

        if (regexMeso != null) {
            if (testes.MESORREGIOES[i].ID.toString().search(regexMeso) != -1) {
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
            if (testes.MESORREGIOES[i].VALOR > regexValorDe && testes.MESORREGIOES[i].VALOR < regexValorAte) {
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
            output[outputIndex] = testes.MESORREGIOES[i];
            outputIndex++;
        }

    }

    /* Micro */

    var toAddMicro = false;

    for (i in testes.MICRORREGIOES) {
        toAddMicro = false;

        if (regexCategoria != null) {
            for (c in testes.MICRORREGIOES[i].CATEGORIA) {
                if (testes.MICRORREGIOES[i].CATEGORIA[c].search(regexCategoria) != -1) {
                    toAddMicro = true;
                    break;
                }
            }

            if (!toAddMicro) {
                continue;
            }
        }

        if (regexMicro != null) {
            if (testes.MICRORREGIOES[i].ID.toString().search(regexMicro) != -1) {
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
            if (testes.MICRORREGIOES[i].VALOR > regexValorDe && testes.MICRORREGIOES[i].VALOR < regexValorAte) {
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
            output[outputIndex] = testes.MICRORREGIOES[i];
            outputIndex++;
        }

    }

    var toAddMunicipios = false;

    /* Municipios*/
    for (i in testes.MUNICIPIOS) {
        toAddMunicipios = false;
        if (regexCategoria != null) {
            for (c in testes.MUNICIPIOS[i].CATEGORIA) {
                if (testes.MUNICIPIOS[i].CATEGORIA[c].search(regexCategoria) != -1) {
                    toAddMunicipios = true;
                    break;
                }
            }

            if (!toAddMunicipios) {
                continue;
            }
        }

        if (regexMeso != null) {
            if (testes.MUNICIPIOS[i].ID_MESO.toString().search(regexMeso) != -1) {
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
            if (testes.MUNICIPIOS[i].ID_MICRO.toString().search(regexMicro) != -1) {
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
            if (testes.MUNICIPIOS[i].VALOR > regexValorDe && testes.MUNICIPIOS[i].VALOR < regexValorAte) {
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
            output[outputIndex] = testes.MUNICIPIOS[i]; //Copia de referencia
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

