let dropMeso = $('#dropdownMeso');
let dropMicro = $('#dropdownMicro');
let dropAtributos = $('#dropdownAtributos');
let dropPeriodos = $('#dropdownPeriodos');

//DROPDOWN MESORREGIÕES

dropMeso.empty();
dropMeso.append('<option selected="true">Mesorregião</option>');
dropMeso.prop('selectedIndex', 0);

const jsonData = getUrlJsonEstadoGeral();

$.each(jsonEstadoGeral.MESORREGIOES, function (key, value) {
   dropMeso.append($('<option></option>').attr('value', value.ID).text(value.NOME_MESORREGIAO));
})

//DROPDOWN MICRORREGIÕES

dropMicro.empty();
dropMicro.append('<option selected="true">Microrregião</option>');
dropMicro.prop('selectedIndex', 0);

$.each(jsonEstadoGeral.MICRORREGIOES, function (key, value) {
	dropMicro.append($('<option></option>').attr('value', value.ID).text(value.NOME_MICRORREGIAO));
})

//DROPDOWN ATRIBUTOS

dropAtributos.empty();
dropAtributos.prop('selectedIndex', 0);

let atributos = jsonEstadoGeral.MESORREGIOES[0].ATRIBUTOS;

atributos.forEach(myFunction);

function myFunction(item, index) {
  dropAtributos.append($('<option></option>').attr('value', index).text(item));
}

function checkAlert(evt){
	indexAtributo = evt.target.value;
	//radarOnMeso();
	lineChart(config.PERIODOS, currentPlace.ATRIBUTOS[indexAtributo].VALORES, currentPlace.NOME_MUNICIPIO);
	setup();
	gerarArrayTreeMap(indexAtributo, indexAtributo);
	drawChart();
}

function getCurrentAttribute(){
	for(let icount of dropAtributos[0]){
		if(icount.selected){
			return icount.innerHTML;
		}
	}
}

//DROPDOWN PERIODOS

dropPeriodos.empty();
dropPeriodos.prop('selectedIndex', 0);

let time = periodos;

time.forEach(addOption);

function addOption(item, index) {
  dropPeriodos.append($('<option></option>').attr('value', index).text(item));
}

function changeTime(evt){
	mudaPeriodo(time[evt.target.value]);
	colorirMun(cidades);
	atualizarVisoes();
}

function changeColorOption(evt){
	estrategiaColoracao = evt.target.value;
	//console.log(estrategiaColoracao);
	colorirMun(cidades);
}