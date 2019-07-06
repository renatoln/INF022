var mapa = Snap('#mapa'); // Passa ao Snap o id da tag <svg> de trabalho
var svg = 'maps/bahia-mod.svg';

loadSVG(svg);

// Recebe o endereço de um svg como parâmetro e chama a funcao que o carrega
function loadSVG(svgParam) {
      Snap.load(svgParam, onSVGLoaded);
}

// Funcao callback chamada ao carregar um svg no Snap, passada como parametro no Snap.load()
function onSVGLoaded(data) {
      var url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/";
      var jsonResponse;
      var bahia;
      var municipios;
      
      // Adiciona o svg dentro da tag <svg> com o id (nesse caso, #mapa) passado para o snap
      // Obs.: Realmente faz um append. Se existir dados, os novos dados serao acrescentados no final
      mapa.append(data); 

      // Seleciona e remove o svg carregado da página. Importante para mudanca de svg.
      function clearSVG() {
            mapa.selectAll('#mapa *')
                  .forEach(element => {
                        element.remove();
                  });
      }

      // Carrega o mapa do Brasil ao clicar com o botao direito
      // no mapa da bahia
      $('.mun').bind('contextmenu', function () {
            clearSVG();
            loadSVG('maps/Brasil.svg');
            return false;     // Faz o menu nao aparecer com o clique do botao direito
      });

      // Carrega o mapa da Bahia ao clicar com o botao esquerdo
      // na bahia, no mapa do brasil
      if(bahia = mapa.select('#Bahia')){
            console.log(bahia);
            bahia.click(()=>  {
                  clearSVG();
                  loadSVG('maps/bahia-mod.svg');
            });
      }

      // Insere funcionalidades de mouseover e click nos municipios
      if(municipios = mapa.select('#Municipios')) {
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
}