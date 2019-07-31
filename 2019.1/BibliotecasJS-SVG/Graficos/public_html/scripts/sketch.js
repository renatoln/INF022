let municipios = [];
let container = document.getElementsByClassName("mun");

function preload(){
	municipios = loadJSON('jsons/MUNICIPIOS_JSON_EVOLUCAO.json',preencherMapa);
}

function randomic(){
let vet = [];
	for(let x = 0; x < 4;x++){
		vet.push(Math.floor((Math.random() * 5499)+500));
	}
	return vet;
}


function preencherMapa(){
	let id = "";
	/*let value = [];
	let periodo = ["Jan/2016","Jan/2017","Jan/2018","Jan/2019"];*/
	/*
	for(let k in municipios){
		value = randomic();
		municipios[k]["value"] = value;
		municipios[k]["periodo"] = periodo;
	}
        */
	for(let i of container){
		id = i.getAttribute("id");
		id = id.replace("mun_","");
		for(let j in municipios.MUNICIPIOS){
                                if(municipios.MUNICIPIOS[j].ID == id){
				i.setAttribute("name",municipios.MUNICIPIOS[j].NOME_MUNICIPIO);
				i.addEventListener("click",function(){
                                        console.log(municipios.MUNICIPIOS[j].VALORES);
                                        console.log(municipios.MUNICIPIOS[j].PERIODO);
					linechart(municipios.MUNICIPIOS[j].VALORES,municipios.MUNICIPIOS[j].PERIODO);
				})
			}
		}
	}
}

function setup(){
	

}