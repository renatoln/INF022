//###### Preencher Mesorregiões ######//
let dropMeso = $('#dropdownMeso');

dropMeso.empty();
dropMeso.append('<option selected="true">Mesorregião</option>');
dropMeso.prop('selectedIndex', 0);


$.each(jsonEstadoGeral.MESORREGIOES, function (key, value) {
	dropMeso.append($('<option></option>').attr('value', value.ID).text(value.NOME_MESORREGIAO));
})

//###### Preencher Microrregiões ######//
let dropMicro = $('#dropdownMicro');

dropMicro.empty();
dropMicro.append('<option selected="true">Microrregião</option>');
dropMicro.prop('selectedIndex', 0);


$.each(jsonEstadoGeral.MICRORREGIOES, function (key, value) {
	dropMicro.append($('<option></option>').attr('value', value.ID).text(value.NOME_MICRORREGIAO));
})


//###### Preencher Atributos ######//
let dropAtributos = $('#dropdownAtributos');

dropAtributos.empty();
dropAtributos.prop('selectedIndex', 0);

let atributos = jsonEstadoGeral.MESORREGIOES[0].ATRIBUTOS;

atributos.forEach(myFunction);

function myFunction(item, index) {
  dropAtributos.append($('<option></option>').attr('value', index).text(item));
}