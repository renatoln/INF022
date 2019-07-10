let vet = document.getElementsByTagName('a');
//console.log(vet);
//console.log(vet[0].href.animVal);

for(let i = 0; i < vet.length;i++){
	vet[i].addEventListener("click",function(){
		if(vet[i].href.animVal == "#bahia"){
			window.location.href = "bahia.html";
		}
	})
}