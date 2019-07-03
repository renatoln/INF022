var m = Snap('#mapa');
Snap.load('maps/mesorregioes.svg', onSVGLoaded);

function onSVGLoaded(data) {
    m.append( data );
    
    var g = m.select('#Mesorregioes');
    
    $.each(g.selectAll("path").items, function() {
            this.attr( { 'style' :  "fill:#EEDDB3" }  ); 

            this.hover(
                  function() {
                        this.attr({ 'style' :  "fill:red" });
                  },
                  function () {
                        this.attr({ 'style' :  "fill:#EEDDB3" });
                  }
            )
          
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