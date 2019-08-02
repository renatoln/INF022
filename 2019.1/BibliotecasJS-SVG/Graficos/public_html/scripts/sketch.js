let municipios = [];
let city = [];
let container = document.getElementsByClassName("mun");
let url = 'jsons/MUNICIPIOS_JSON_TESTES.json';

function preload(){
	municipios = loadJSON('jsons/MUNICIPIOS_JSON_EVOLUCAO.json',preencherMapa);
        city = loadJSON(url);
}

function preencherMapa(){
	let id = "";
	for(let i of container){
		id = i.getAttribute("id");
		id = id.replace("mun_","");
		for(let j in municipios.MUNICIPIOS){
                                if(municipios.MUNICIPIOS[j].ID == id){
				i.setAttribute("name",municipios.MUNICIPIOS[j].NOME_MUNICIPIO);
				i.addEventListener("click",function(){
					linechart(municipios.MUNICIPIOS[j].VALORES,municipios.MUNICIPIOS[j].PERIODOS);
				});
			}
		}
	}
}

function setup(){
	definirCor();
}

/************************ -- CÓDIGO DE PREENCHIMENTO DO MAPA -- ************************************/

function colorirMunicipio(idMunicipio, cor){
    encontrarMunicipio(idMunicipio);
    let mun =  encontrarMunicipio(idMunicipio);
    mun.style.fill = cor;
}

function encontrarMunicipio(idMunicipio){
    let id = "";
    for (let i = 0; i < container.length;i++){
        id = container[i].id;
        id = id.replace("mun_","");
        
        if(idMunicipio == id){
            return container[i];
        }
    }
}

function definirCor() {
    let min = city.MIN_Valor; 
    let max = city.MAX_Valor;

    let faixa = (max - min) / 5;
    let grupos = [6];
    grupos[0] = min;
    
    for (let i = 1; i < 6; i++) {
        grupos[i] = grupos[i - 1] + faixa;
    }

    let cor1 = '#ff2b1f';
    let cor2 = '#e63025';
    let cor3 = '#bf2d24';
    let cor4 = '#9c251e';
    let cor5 = '#821f19';
    let cor6 = '#631813';
    
    var count = city.MUNICIPIOS.length;
    
    for (let i = 0; i < city.MUNICIPIOS.length; i++) {
        if (city.MUNICIPIOS[i].VALOR < grupos[0]) { //como faz a chamada do atributo valor da classe municiopios
            colorirMunicipio(city.MUNICIPIOS[i].ID, cor1);
        } else if (city.MUNICIPIOS[i].VALOR < grupos[1]) {
            colorirMunicipio(city.MUNICIPIOS[i].ID, cor2);
        } else if (city.MUNICIPIOS[i].VALOR < grupos[2]) {
            colorirMunicipio(city.MUNICIPIOS[i].ID, cor3);
        } else if (city.MUNICIPIOS[i].VALOR < grupos[3]) {
            colorirMunicipio(city.MUNICIPIOS[i].ID, cor4);
        } else if (city.MUNICIPIOS[i].VALOR < grupos[4]) {
            colorirMunicipio(city.MUNICIPIOS[i].ID, cor5);
        } else {
            colorirMunicipio(city.MUNICIPIOS[i].ID, cor6);
        }
    }
}