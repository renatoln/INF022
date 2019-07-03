var mapa = Snap('#mapa');

Snap.load('maps/bahia-mod.svg', onSVGLoaded);

function onSVGLoaded(data) {
      var url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/";
      var jsonResponse;
      var bahia;
      mapa.append(data);

      var municipios = mapa.select('#Municipios');

      $('.mun').bind('contextmenu', function () {
            let slicedId = this.id.slice(4, 11);
            let jsondata = fetch(url + slicedId)
                  .then(res => res.json())
                  .then(data => jsonResponse = data)
                  .then(() => location.href = 'index.html')
            return false;     // Faz o menu não aparecer com o clique do botão direito
      });

      $.each(municipios.selectAll("path").items, function () {

            this.hover(
                  () => {
                        let slicedId = this.attr('id').slice(4, 11);
                        let jsondata = fetch(url + slicedId)
                              .then(res => res.json())
                              .then(data => jsonResponse = data)
                              .then(() => this.append(Snap.parse('<title>Cidade: ' + jsonResponse.nome
                                    + '&#013 Microrregião: ' + jsonResponse.microrregiao.nome
                                    + '&#013 Mesorregião: ' + jsonResponse.microrregiao.mesorregiao.nome
                                    + '</title>')));

                        this.attr({ 'fill': 'red' });
                  },
                  () => {
                        this.attr({ 'fill': '#EEDDB3' });
                  }
            )

            this.click(() => {
                  let slicedId = this.attr('id').slice(4, 11);
                  let jsondata = fetch(url + slicedId)
                        .then(res => res.json())
                        .then(data => jsonResponse = data)
                        .then(() => this.append(alert('Ir para mesorregião ' + jsonResponse.microrregiao.mesorregiao.nome
                              + ' de ID ' + jsonResponse.microrregiao.mesorregiao.id)));
            });
      });
}
