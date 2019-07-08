function setup(){
	loadJSON("http://servicodados.ibge.gov.br/api/v1/localidades/estados/29/municipios",gotData);
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
			}
		}
	}

}