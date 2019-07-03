var m = Snap('#mapa');
Snap.load('maps/microrregioes.svg', onSVGLoaded);

function onSVGLoaded(data) {
    m.append( data );
    
    var g = m.select('#Microrregioes');
    
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
            let url = "https://servicodados.ibge.gov.br/api/v1/localidades/microrregioes/" + slicedId;
            
            this.attr( { 'style' :  "fill:#ffffff" }  );
            let jsondata = fetch(url).then(
                  function(u){ return u.json();}
            ).then(
                  function(json){
                        alert('Id: ' + json.id + '\nMicrorregião: ' + json.nome );
                  }
            )
          });
    });
    
}