let evolucao = [];
let testes = [];

let cores = {
	cor1 : "#f59042",
	cor2 : "#ff8c33",
	cor3 : "#f57f22",
	cor4 : "#f07413",
	cor5 : "#de690d",
	cor6 : "#d65f02"
};

function preload(){
	evolucao = loadJSON("/jsons/MUNICIPIOS_JSON_EVOLUCAO.json",gotData);
	testes = loadJSON("/jsons/MUNICIPIOS_JSON_TESTES.json",gotData);
}

function gotData(data){
	//console.log(data);
}


function setup(){
	let id;
	//Compara o mapa com o JSON
	for(let icount in cidades){
		id = cidades[icount].node.attributes.id.value;
		id = id.replace("mun_","");
		for(let jcount in testes.MUNICIPIOS){
			//QUANDO ENCONTRA IDS IGUAIS, ELE COLORE A CIDADE DE ACORDO COM O VALOR NO JSON
			if(id == testes.MUNICIPIOS[jcount].ID){
				cidades[icount].node.attributes.fill.value = definirCor(testes.MUNICIPIOS[jcount].VALOR);
				break;
			}
		}
	}
}

function definirCor(valor) {
	let min = testes.MIN_Valor;
	let max = testes.MAX_Valor;
    let faixa = (max - min) / 6;
    let grupos = [6];
    grupos[0] = min;

    for (let i = 1; i < 6; i++) {
        grupos[i] = grupos[i - 1] + faixa;
    }

    if (valor < grupos[1]) {
        console.log("cor1");
        return cores.cor1;
    } else if (valor < grupos[2]) {
        console.log("cor2");
        return cores.cor2;
    } else if (valor < grupos[3]) {
        console.log("cor3");
        return cores.cor3;
    } else if (valor < grupos[4]) {
        console.log("cor4");
        return cores.cor4;
    } else if (valor < grupos[5]) {
        console.log("cor5");
        return cores.cor5;
    } else {
        console.log("cor6");
        return cores.cor6;
    }

}