var m = Snap('#mapa');
Snap.load('/maps/bahia-mod.svg', onSVGLoaded);

function onSVGLoaded(data) {
      m.append( data );
      
      var g = m.select('#Municipios');
      
      $.each(g.selectAll("path").items, function() {
            
            this.hover(
                  function () {
                        this.attr({ 'fill': 'red'});
                  },
                  function () {
                        this.attr({ 'fill': '#EEDDB3'});
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