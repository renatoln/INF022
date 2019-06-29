var m = Snap('#mapa');
Snap.load('maps/bahia-mod.svg', onSVGLoaded);

function onSVGLoaded(data) {
      m.append( data );

      var g = m.select('#Municipios');

      $.each(g.selectAll("path").items, function() {
                  this.hover(
                  function () {
                        let slicedId = this.attr('id').slice(4,11);
                        let url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/" + slicedId;
                        let jsonResponse;

                        let jsondata = fetch(url)
                        .then(res => res.json())
                        .then(data => jsonResponse = data)
                        .then(() => this.append(Snap.parse('<title><p> Cidade: ' + jsonResponse.nome
                                    + '&#013 Microrregião: ' + jsonResponse.microrregiao.nome
                                    + '&#013 Mesorregião: ' + jsonResponse.microrregiao.mesorregiao.nome
                                    + '</title>')))

                        this.attr({ 'fill': 'red' });
                  },
                  function () {
                        this.attr({ 'fill': '#EEDDB3'});
                  }
            )

            this.click(function() {
                  let slicedId = this.attr('id').slice(4,11);
                  let url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/" + slicedId;
                  let jsonResponse;

                  let jsondata = fetch(url)
                  .then(res => res.json())
                  .then(data => jsonResponse = data)
                  .then(() => this.append(alert('Id: ' + jsonResponse.id + '\nCidade: ' + jsonResponse.nome )))
            });
      });
}
