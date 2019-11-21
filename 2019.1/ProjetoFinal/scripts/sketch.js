let config = [];
let geral = [];
let popUp = document.getElementById("popUp");
let opcoes = popUp.children[0].children;
let popOUT = document.getElementById("municipios");
let bar = document.getElementById("barChart");
let sun = document.getElementById("sunChart");
let radar = document.getElementById("radarChart");
let compare = document.getElementById("atrCompare");
var isDirty = false;
//let arrayTreeMap = [];
//let tam = null;
//let numColor = null;


var cinza = "#8C92AC";

// do verde para o vermelho
let cores = {
	cor1 : "#00ff00",
	cor2 : "#ffff00",
	cor3 : "#f5b342",
	cor4 : "#f56342",
	cor5 : "#ff0000",
	cor6 : "#ed0505"
};

/*

cor1 : "#f5f542",
	cor2 : "#f5e942",
	cor3 : "#f5b342",
	cor4 : "#f59942",
	cor5 : "#f56342",
	cor6 : "#ed0505"
*/

//CARREGA O JSON
function preload() {
	config = loadJSON(getUrlConfig());
	geral = loadJSON(getUrlJsonEstadoGeral());
	jsonEstadoGeral = geral;

	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

}

async function toColorBySearch(jsonFiltered) {
	let id;

	//Apenas municipios possuem ID maior ;; jsonCount in jsonFiltered if(jsonFiltered[jsonCount].ID > 99999){

	isDirty = true;

	for (icount in cidades) {

		id = cidades[icount].node.attributes.id.value;

		if (id.includes("mun_")) {

			id = id.replace("mun_", "");
			var idInt = parseInt(id, 10);

			for (var jsonCount = 0; jsonCount < jsonFiltered.length; jsonCount++) {

				if (jsonFiltered[jsonCount].ID > 99999) //Apenas municipios possuem ID maior que tal valor
				{
					if (idInt === jsonFiltered[jsonCount].ID) //Não podemos colorir quem na busca está
					{
						break;
					}
					else if (jsonCount == jsonFiltered.length - 1) //andou todo o vetor e não encontrou ninguém identico ao ID
					{
						cidades[icount].node.attributes.fill.value = cinza;
					}
				}
			}
		}
	}

}

function clearSearchOnMap() {
	let id;

	if (isDirty) {
		for (let icount in cidades) {
			id = cidades[icount].node.attributes.id.value;
			id = id.replace("mun_", "");
			for (let jcount in geral.MUNICIPIOS) {

				//QUANDO ENCONTRA IDS IGUAIS, ELE COLORE A CIDADE DE ACORDO COM O VALOR NO JSON
				if (id == geral.MUNICIPIOS[jcount].ID) {
					cidades[icount].node.attributes.fill.value = definirCor(geral.MUNICIPIOS[jcount].VALORES[indexAtributo]);
					break;
				}
			}
		}
		isDirty = false;
	}
}

//FUNÇÃO QUE CARREGA O PRIMEIRO MAPA
function primeiroMapa() {
	let capital = searchEquivalent(capitalId, jsonEstadoEvolucao.MUNICIPIOS);
	attributeCompare(config.PERIODOS, capital.ATRIBUTOS, capitalNome);
	currentPlace = capital;
}

//FUNÇÃO QUE ADICIONA O POPUP EM CADA CIDADE E TB ADICIONA OS GRAFICOS
function addPopUp(local, localEvolucao) {

	//FUNÇÃO QUE ABRE O POPUP
	local.node.addEventListener("contextmenu", function (ev) {
		ev.preventDefault();
		mouseX = ev.clientX;
		mouseY = ev.clientY;

		//ADICIONA FUNÇÃO DE BARCHART NO CLIQUE
		bar.addEventListener("click", function () {
			if (local.node.attributes.id.value.includes("mun_"))
				barChart(config.PERIODOS, localEvolucao.ATRIBUTOS[indexAtributo].VALORES, localEvolucao.NOME_MUNICIPIO);
			else
				if (local.node.attributes.id.value.includes("mic_"))
					barChart(config.PERIODOS, localEvolucao.ATRIBUTOS[indexAtributo].VALORES, localEvolucao.NOME_MICRORREGIAO);
				else
					barChart(config.PERIODOS, localEvolucao.ATRIBUTOS[indexAtributo].VALORES, localEvolucao.NOME_MESORREGIAO);
		})

		//ADICIONA FUNÇÃO DE SUNBURST NO CLIQUE
		sun.addEventListener("click", function () {
			if (local.node.attributes.id.value.includes("mic_"))
				sunburst(localEvolucao.NOME_MICRORREGIAO);
			else
				sunburst(localEvolucao.NOME_MESORREGIAO);
			if (local.node.attributes.id.value.includes("mun_"))
				sunburstAll();
		})

		//ABRE UM LINECHART QUANDO CLICA NO POPUP
		compare.addEventListener("click", function () {
			if (local.node.attributes.id.value.includes("mun_"))
				attributeCompare(config.PERIODOS, localEvolucao.ATRIBUTOS, localEvolucao.NOME_MUNICIPIO);
			else
				if (local.node.attributes.id.value.includes("mic_"))
					attributeCompare(config.PERIODOS, localEvolucao.ATRIBUTOS, localEvolucao.NOME_MICRORREGIAO);
				else
					attributeCompare(config.PERIODOS, localEvolucao.ATRIBUTOS, localEvolucao.NOME_MESORREGIAO);
		})

		radar.addEventListener("click", function () {
			
			this.removeEventListener('click', arguments.callee, false);

			if (local.node.attributes.id.value.includes("mun_"))
				radarOnMuni(localEvolucao.NOME_MUNICIPIO);
			else
				if (local.node.attributes.id.value.includes("mic_"))
					radarOnMicro(localEvolucao.NOME_MICRORREGIAO);
				else
					radarOnMeso(localEvolucao.NOME_MESORREGIAO);
		}, false)

		//DEFINE A POSICAO ONDE O POPUP FICARÁ
		popUp.style.top = `${mouseY / 3}px`;
		popUp.style.left = `${mouseX / 3}px`;
		popUp.style.visibility = "visible";
		return false;
	}, false);

	//FUNÇÃO QUE REMOVE O POPUP QUANDO CLICA FORA
	popOUT.addEventListener("click", function () {
		popUp.style.visibility = "hidden";
	})

}

function colorByLog(valor) {
	let min = Math.log10(geral.METADADOS.MIN_VALORES[indexAtributo]);
	let max = Math.log10(geral.METADADOS.MAX_VALORES[indexAtributo]);
	let faixa = (max - min) / 6;
	let grupos = [6];
	grupos[0] = min + faixa;

	for (let i = 1; i < 6; i++) {
		grupos[i] = grupos[i - 1] + faixa;
	}

	if (valor == 0) return cinza;
	valor = Math.log10(valor);

	if (valor < grupos[0]) {
		return cores.cor1;
	} else if (valor < grupos[1]) {
		return cores.cor2;
	} else if (valor < grupos[2]) {
		return cores.cor3;
	} else if (valor < grupos[3]) {
		return cores.cor4;
	} else if (valor < grupos[4]) {
		return cores.cor5;
	} else {
		return cores.cor6;
	}
}

function colorByMinMax(valor) {
	let min = geral.METADADOS.MIN_VALORES[indexAtributo];
	let max = geral.METADADOS.MAX_VALORES[indexAtributo];
	let faixa = (max - min) / 6;
	let grupos = [6];
	grupos[0] = min + faixa;

	for (let i = 1; i < 6; i++) {
		grupos[i] = grupos[i - 1] + faixa;
	}
	if (valor == 0) return cinza;

	if (valor < grupos[0]) {
		return cores.cor1;
	} else if (valor < grupos[1]) {
		return cores.cor2;
	} else if (valor < grupos[2]) {
		return cores.cor3;
	} else if (valor < grupos[3]) {
		return cores.cor4;
	} else if (valor < grupos[4]) {
		return cores.cor5;
	} else if (valor <= grupos[5]) {
		return cores.cor6;
	} else {
		return cinza;
	}

}

function colorByPercentis(valor) {
	let percentis = geral.METADADOS.PERCENTIS[indexAtributo].VALORES;

	if (valor == 0) return cinza;
	if (valor < percentis[0]) {
		return cores.cor1;
	} else if (valor < percentis[1]) {
		return cores.cor2;
	} else if (valor < percentis[2]) {
		return cores.cor3;
	} else if (valor < percentis[3]) {
		return cores.cor4;
	} else {
		return cores.cor5;
	}

}
function definirCor(valor) {
	if (estrategiaColoracao == colorMinMax)
		return colorByMinMax(valor);

	else if (estrategiaColoracao == colorPercentis)
		return colorByPercentis(valor);

	else if (estrategiaColoracao == colorLog)
		return colorByLog(valor);

}

//RECEBE UM ID E ENCONTRA O MUNICIPIO EQUIVALENTE NO JSON DE EVOLUCAO 
function searchEquivalent(id, vetor) {
	for (let jcount of vetor) {
		if (jcount.ID == id) {
			return jcount;
		}
	}
}

//FUNÇÃO DE COLORAÇÃO DO MAPA
function setup() {
	primeiroMapa();
	
	let id;
	let localEvolucao;

	//GERA O POPUP DAS CIDADES
	for (let icount in cidades) {
		generatePopUp(cidades[icount]);
	}
	//GERA O POPUP DAS MICRORREGIOES
	for (let icount in microrregioes) {
		generatePopUp(microrregioes[icount]);
	}
	//GERA O POPUP DAS MESORREGIOES
	for (let icount in mesorregioes) {
		generatePopUp(mesorregioes[icount]);
	}

	colorirMun(cidades);
}

function generatePopUp(element) {

	let id = element.node.attributes.id.value;
	//VERIFICA SE O ELEMENTO É UMA CIDADE, MICRO OU MESORREGIAO
	if (id.includes("mun_")) {
		id = id.replace("mun_", "");
		for (let jcount in geral.MUNICIPIOS) {
			if (id == geral.MUNICIPIOS[jcount].ID) {
				//element.node.attributes.fill.value = definirCor(geral.MUNICIPIOS[jcount].VALORES[indexAtributo]);
				localEvolucao = searchEquivalent(id, jsonEstadoEvolucao.MUNICIPIOS);
				addPopUp(element, localEvolucao);
				break;
			}
		}
	} else
		if (id.includes("mic_")) {
			id = id.replace("mic_", "");
			for (let jcount in geral.MICRORREGIOES) {
				if (id == geral.MICRORREGIOES[jcount].ID) {
					localEvolucao = searchEquivalent(id, jsonEstadoEvolucao.MICRORREGIOES);
					addPopUp(element, localEvolucao);
					break;
				}
			}
		} else {
			id = id.replace("mes_", "");
			for (let jcount in geral.MESORREGIOES) {
				if (id == geral.MESORREGIOES[jcount].ID) {
					localEvolucao = searchEquivalent(id, jsonEstadoEvolucao.MESORREGIOES);
					addPopUp(element, localEvolucao);
					break;
				}
			}
		}
}


function colorirMun(cidades){
	let id = "";
	let element = null;
	for(let icount in cidades){
		id = cidades[icount].node.attributes.id.value;
		id = id.replace("mun_","");
		element = searchEquivalent(id,jsonEstadoEvolucao.MUNICIPIOS);
		cidades[icount].node.attributes.fill.value = definirCor(element.ATRIBUTOS[indexAtributo].VALORES[periodos.indexOf(periodoAtual)]);
	}
}