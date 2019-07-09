var mapa = Snap('#mapa'); // Passa ao Snap o id da tag <svg> de trabalho
var svg = 'maps/mesorregioes.svg';

loadSVG(svg);

// Recebe o endereço de um svg como parâmetro e chama a funcao que o carrega
function loadSVG(svgParam) {
      Snap.load(svgParam, onSVGLoaded);
}

// Seleciona e remove o svg carregado da página. Importante para mudanca de svg.
function clearSVG() {
      mapa.selectAll('#mapa *')
            .forEach(element => {
                  element.remove();
            });
}

// Funcao callback chamada ao carregar um svg no Snap, passada como parametro no Snap.load()
function onSVGLoaded(data) {
      var url = 'https://servicodados.ibge.gov.br/api/v1/localidades/mesorregioes/';
      var jsonResponse;
      var bahia;
      var microrregioes;
      
      // Adiciona o svg dentro da tag <svg> com o id (nesse caso, #mapa) passado para o snap
      // Obs.: Realmente faz um append. Se existir dados, os novos dados serao acrescentados no final
      mapa.append(data); 
      
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

      // MESORREGIOES
      // Insere funcionalidades de mouseover e click nos MESORREGIOES
      if(microrregioes = mapa.select('#Mesorregioes')) {
            $.each(microrregioes.selectAll("path").items, function () {
                  this.attr({ 'style' :  "fill:#EEDDB3" });
                  this.hover(
                        () => {
                              let slicedId = this.attr('id').slice(4, 8);
                              let jsondata = fetch(url + slicedId)
                                    .then(res => res.json())
                                    .then(data => jsonResponse = data)
                                    .then(() => this.append(Snap.parse('<title>Mesorregião: ' + jsonResponse.nome
                                          + '</title>')));

                              this.attr({ 'style' :  "fill:red" });
                        },
                        () => {
                              this.attr({ 'style' :  "fill:#EEDDB3" });
                        }
                  )
                  
                  this.click(() => {
                        let slicedId = this.attr('id').slice(4, 8);
                        let jsondata = fetch(url + slicedId)
                              .then(res => res.json())
                              .then(data => jsonResponse = data)
                              .then(() => ( $('#modal')
                                                .modal()
                                                .text('Dados da mesorregião ' + jsonResponse.nome 
                                                      + ' de ID '+ slicedId + ':') 
                                          )
                              );
                  });

                  $(document).keydown(function(event) {
                        if(event.ctrlKey) {
                                    alert('open modal');
                        }
                  });


                  // Faz o zoom e centraliza a mesorregiao clicada
/*                   this.click(() => {
                        this.animateSvgFocus( 1000, mina.linear, () => g.animateSvgFocus( 1000, mina.linear));
                  }); */


            });

            $('path').keydown((e) => {
                  if(e.ctrlKey) {
                        this.click(() => {
                              alert('open modal');
                        });
                  }
            });
      }
}