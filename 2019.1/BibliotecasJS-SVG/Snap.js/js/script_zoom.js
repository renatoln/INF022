var mapa = Snap('#mapa'); // Passa ao Snap o id da tag <svg> de trabalho
var svg = 'maps/bahia_zoom.svg';
var regioes = ['#Mesorregioes','#Microrregioes','#Municipios'];
var nivel = 0;
var init = false;

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
      var url = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/";
      var jsonResponse;
      var bahia;
      var regiao = regioes[nivel];
      var regiaoSelecionada;

      // Adiciona o svg dentro da tag <svg> com o id (nesse caso, #mapa) passado para o snap
      // Obs.: Realmente faz um append. Se existir dados, os novos dados serao acrescentados no final
      if(!init)
            mapa.append(data); 
      
      init = true;

      console.log(nivel);

      // Carrega o mapa do Brasil ao clicar com o botao direito
      // no mapa da bahia
/*       $('.mun').bind('contextmenu', function () {
            clearSVG();
            loadSVG('maps/Brasil.svg');
            return false;     // Faz o menu nao aparecer com o clique do botao direito
      }); */

      // Carrega o mapa da Bahia ao clicar com o botao esquerdo
      // na bahia, no mapa do brasil
/*       if(bahia = mapa.select('#Bahia')){
            console.log(bahia);
            bahia.click(()=>  {
                  clearSVG();
                  loadSVG('maps/bahia-mod.svg');
            });
      } */
      regiaoSelecionada = mapa.select(regiao);
      // MUNICIPIOS
      // Insere funcionalidades de mouseover e click nos municipios
      
      console.log(regiaoSelecionada);
      $.each(regiaoSelecionada.selectAll("path").items, function () {
            this.attr({ 'fill': '#EEDDB3' });
            this.hover(
                  () => this.attr({ 'fill': 'red' })
                  ,
                  () => this.attr({ 'fill': '#EEDDB3' })
            )

            this.click(
                  () => {
                        if(nivel<=2){
                              zoomAnimation(this, regiaoSelecionada);                    
                              desceNivel();
                              regiao=regioes[nivel];
                              onSVGLoaded(data);    
                        }
                        else {
                              console.log('nope');
                              alert('Não é mais possivel aproximar');
                        }
                  }
            );
      });

}

function zoomAnimation(path, regiaoSelecionada) {
      path.hover(() => {},() => {});
      path.attr({ 'fill': 'none' });
      path.animateSvgFocus(1000, mina.linear, clearAttr(regiaoSelecionada));
}

function clearAttr(regiaoSelecionada) {
      $.each(regiaoSelecionada.selectAll("path").items, function () {
            this.attr({ 'fill': 'none' });
            this.hover(() => {},() => {});
      });
}

function desceNivel() {
      if(nivel<2)
            nivel++;
      return;
}

function sobeNivel() {
      if(nivel>0)
            nivel--;
      return;
}