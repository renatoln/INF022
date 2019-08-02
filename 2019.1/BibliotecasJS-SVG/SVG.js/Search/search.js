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

    var output;

    //;~; me ajude a me ajudar, soda

    for (i in json.Municipios) {
        var toAddOnOutput = false;

        if (!regexCategoria) { //TODO LOOP
            if (json.Municipios[i].CATEGORIA[0].search(regexCategoria) != -1) {
                toAddOnOutput = true;
            }
            else { //Quer dizer que o parametro foi especificado, mas não corresponde no objeto atual, logo, devemos encerrar o loop
                toAddOnOutput = false;
            }

            if(!toAddOnOutput)
            {
                continue;
            }
        }

        if (!regexMeso) {
            if (json.Municipios[i].ID_MESO.toString().search(regexMeso) != -1) {
                toAddOnOutput = true;
            }
            else {
                toAddOnOutput = false;
            }

            if(!toAddOnOutput)
            {
                continue;
            }
        }

        if (!regexMicro) {
            if (json.Municipios[i].ID_MICRO.toString().search(regexMicro) != -1) {
                toAddOnOutput = true;
            }
            else {
                toAddOnOutput = false;
            }

            if(!toAddOnOutput)
            {
                continue;
            }
        }

        if (!regexValorDe && !regexValorAte) {
            if (json.Municipios[i].VALOR > regexValorDe && json.Municipios[i].VALOR < regexValorAte) {
                toAddOnOutput = true;
            }
            else {
                toAddOnOutput = false;
            }

            if(!toAddOnOutput)
            {
                continue;
            }
        }

        if(toAddOnOutput)
        {
            output += json.Municipios[i]; // :think: isso é valido em js... lul
        }

    }
    return output;
}