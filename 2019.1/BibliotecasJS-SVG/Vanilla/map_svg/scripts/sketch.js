function setup(){
	loadJSON("http://servicodados.ibge.gov.br/api/v1/localidades/estados/29/municipios",gotData);
	loadJSON("http://servicodados.ibge.gov.br/api/v1/localidades/estados/29/microrregioes",gotMicro);
	loadJSON("http://servicodados.ibge.gov.br/api/v1/localidades/estados/29/mesorregioes",gotMeso);
}

//Essa função pega o nome das cidades no arquivo JSON e passa para o html
function gotData(data){
	let id;
	for(let icount = 0; icount < vetor.length;icount++){
		id = vetor[icount].getAttribute("id");
		id = id.replace("mun_","");
		for(let jcount = 0; jcount < data.length; jcount++){
			if(data[jcount].id == id){
				id = vetor[icount].setAttribute("nome",data[jcount].nome);
				jcount = data.length;
			}
		}
	}
}

function gotMicro(data){
	let id = null;
	for(let i = 0; i < micro.length;i++){
		id = micro[i].getAttribute("id");
		id = id.replace("mic_","");
		for(let j = 0; j < data.length; j++){
			if(data[j].id == id){
				micro[i].setAttribute("name",data[j].nome);
				j = data.length;
			}
		}
	}
}

function gotMeso(data){
	let id;
	for(let icount = 0; icount < meso.length;icount++){
		id = meso[icount].getAttribute("id");
		id = id.replace("mes_","");
		for(let jcount = 0; jcount < data.length; jcount++){
			if(data[jcount].id == id){
				meso[icount].setAttribute("name",data[jcount].nome);
				jcount = data.length;
			}
		}
	}
}