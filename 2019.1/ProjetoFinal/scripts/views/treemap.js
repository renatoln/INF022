//treemap
let arrayTreeMap = [];
let tam = 0;
let numColor = 0;

function atualizaTreeMap(){
	indTam = $('#dropdownTamanho').val();
	if(indTam == null)
		indTam = 0;
	indCor = $('#dropdownCor').val();
	if(indCor == null)
		indCor = 0;
	drawChart(indTam, indCor);
}

function gerarArrayTreeMap(tam, numColor){
	var i = 0;
	var sizeTam = jsonEstadoGeral.MESORREGIOES[i].ATRIBUTOS[tam];
	var cor = jsonEstadoGeral.MESORREGIOES[i].ATRIBUTOS[numColor];
	arrayTreeMap = [];
	arrayTreeMap.push(['Localidade', 'Parent', sizeTam, cor]);
	arrayTreeMap.push(['ba', null, 0, 0]);
	let mesoArray = [];
	for(let iCont in jsonEstadoGeral.MESORREGIOES){
		var codM = "" + jsonEstadoGeral.MESORREGIOES[iCont].ID;
		var nomeM = jsonEstadoGeral.MESORREGIOES[iCont].NOME_MESORREGIAO;
		mesoArray.push(codM.concat(" - ", nomeM), 'ba',
						jsonEstadoGeral.MICRORREGIOES[iCont].VALORES[tam],
						jsonEstadoGeral.MICRORREGIOES[iCont].VALORES[numColor]);
		
		arrayTreeMap.push(mesoArray);
		mesoArray = [];
	}
	//adicionando as microrregioes
	let microArray = [];
	for(let iCont in jsonEstadoGeral.MICRORREGIOES){
		for(let i in jsonEstadoGeral.MESORREGIOES){
			if(jsonEstadoGeral.MICRORREGIOES[iCont].ID_MESO == jsonEstadoGeral.MESORREGIOES[i].ID){
				var codM = "" + jsonEstadoGeral.MICRORREGIOES[iCont].ID;
				var nomeM = jsonEstadoGeral.MICRORREGIOES[iCont].NOME_MICRORREGIAO;
				var sup =  "" + jsonEstadoGeral.MESORREGIOES[i].ID;
				microArray.push(codM.concat(" - ", nomeM), 
								sup.concat(" - ", jsonEstadoGeral.MESORREGIOES[i].NOME_MESORREGIAO), 
								jsonEstadoGeral.MICRORREGIOES[iCont].VALORES[tam],
								jsonEstadoGeral.MICRORREGIOES[iCont].VALORES[numColor]);
				arrayTreeMap.push(microArray);
				microArray = [];
				break;
			}
		}
	}
	//adicionando os municipios ao arrayTreeMap
	let munArray = [];
	for(let iCont in jsonEstadoGeral.MUNICIPIOS){
		for(let i in jsonEstadoGeral.MICRORREGIOES){
			if(jsonEstadoGeral.MUNICIPIOS[iCont].ID_MICRO == jsonEstadoGeral.MICRORREGIOES[i].ID){
				var codM = "" + jsonEstadoGeral.MUNICIPIOS[iCont].ID;
				var nomeM = jsonEstadoGeral.MUNICIPIOS[iCont].NOME_MUNICIPIO;
				var sup = "" + jsonEstadoGeral.MICRORREGIOES[i].ID;
					munArray.push(codM.concat(" - ", nomeM), 
									sup.concat(" - ", jsonEstadoGeral.MICRORREGIOES[i].NOME_MICRORREGIAO),
									jsonEstadoGeral.MUNICIPIOS[iCont].VALORES[tam],
									jsonEstadoGeral.MUNICIPIOS[iCont].VALORES[numColor]);
					arrayTreeMap.push(munArray);
					munArray = [];
				break;
			}
			
		}		
		
	}
}

google.charts.load('current', {'packages':['treemap']});
	  google.charts.setOnLoadCallback(drawChart);
	  function drawChart(tam, cor) {
		gerarArrayTreeMap(tam,cor);
		let data = google.visualization.arrayToDataTable(arrayTreeMap);
		tree = new google.visualization.TreeMap(document.getElementById('chart_div_tree'));
		tree.draw(data, {
		  minColor: '#f00',
		  midColor: '#ddd',
		  maxColor: '#0d0',
		  headerHeight: 15,
		  fontColor: 'black',
		  showScale: true,
		  highlightOnMouseOver: true,
		  generateTooltip: showFullTooltip
		});
		function showFullTooltip(row, size, value) {

			return '<div style="background:Black; padding:2px; border-style:solid; color:White">' +
					'<span style="font-family:Courier; color:White"><n>'
					+data.getValue(row, 0)+ '<br>' + 
					+ data.getColumnLabel(2) + ': ' + size + '<br>' 
					+ data.getColumnLabel(3) + ': ' + value + '</div>';
		  }	
	  }
