var m = Snap('#mapa');
Snap.load('maps/brasil.svg', onSVGLoaded);

function onSVGLoaded(data) {
    m.append( data );
    
    var g = m.select('#Estados');
    
      $.each(g.selectAll("path").items, function() {

            document.getElementById('Bahia').onclick = function () {
                  location.href = 'municipios.html';
            };
          
            this.click(function() {
                  let slicedId = this.attr('id').slice(4,11);
                  let url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/" + slicedId;
                  
                  let jsondata = fetch(url).then(
                        function(u){ return u.json();}
                  ).then(
                        function(json){
                              alert('Id: ' + json.id + '\nCidade: ' + json.nome );
                        }
                  )
            });
    });
}