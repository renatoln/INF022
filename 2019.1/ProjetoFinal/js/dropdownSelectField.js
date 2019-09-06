let dropMeso = $('#dropdownMeso');

dropMeso.empty();
dropMeso.append('<option selected="true">Mesorregião</option>');
dropMeso.prop('selectedIndex', 0);

const jsonData = getUrlJsonEstadoGeral();

$.each(jsonEstadoGeral.MESORREGIOES, function (key, value) {
   dropMeso.append($('<option></option>').attr('value', value.ID).text(value.NOME_MESORREGIAO));
})

let dropMicro = $('#dropdownMicro');

dropMicro.empty();
dropMicro.append('<option selected="true">Microrregião</option>');
dropMicro.prop('selectedIndex', 0);

$.each(jsonEstadoGeral.MICRORREGIOES, function (key, value) {
	dropMicro.append($('<option></option>').attr('value', value.ID).text(value.NOME_MICRORREGIAO));
})

let dropAtributos = $('#dropdownAtributos');

dropAtributos.empty();
dropAtributos.prop('selectedIndex', 0);

let atributos = jsonEstadoGeral.MESORREGIOES[0].ATRIBUTOS;

atributos.forEach(myFunction);

function myFunction(item, index) {
  dropAtributos.append($('<option></option>').attr('value', index).text(item));
}

function checkAlert(evt){
	indexAtributo = evt.target.value;
	lineChart(config.PERIODOS, currentPlace.ATRIBUTOS[indexAtributo].VALORES, currentPlace.NOME_MUNICIPIO);
	for (let icount in cidades) {
		generatePopUp(cidades[icount]);
	}
}