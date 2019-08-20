let cidades = [];
var mapa = Snap('#mapa'); // Passa ao Snap o id da tag <svg> de trabalho
let estado = 'ba'; //es ba
let capitalId = 2927408; //vitoria = 3205309; salvador = 2927408
var svg = 'maps/'+estado+'.svg';
var descricaoRegiao = ['mesorregião', 'microrregião', 'município'];
var regioes = ['#Mesorregioes','#Microrregioes','#Municipios'];
var classes = ['.mesoreg','.micreg','.mun'];
var urlPath;
var nivel = 0;
var init = false;
var jsonResponse;
var regiao = regioes[nivel];
var regiaoSelecionada;
var zPressionado = false;
var estadoJson;

$.getJSON("../jsons/"+estado+".json", function (data) {
      estadoJson = data;
});

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
      // Adiciona o svg dentro da tag <svg> com o id (nesse caso, #mapa) passado para o snap
      // Obs.: Realmente faz um append. Se existir dados, os novos dados serao acrescentados no final
      if(!init)
            mapa.append(data); 
      
      init = true;

      regiaoSelecionada = mapa.select(regiao);

      let selected = regiaoSelecionada.selectAll("path").items; 
      
      setAtributosCamada(selected);

      //Essa linha abaixo que pega o PATH de cada municipio no SVG
      cidades = mapa.select("#Municipios").selectAll("path").items;
}

// Seta os atributos da camada atual do mapa
function setAtributosCamada(selected){
      $.each(selected, function () {
            setHovers(this);
            this.click(
                  (e) => {
                        if (zPressionado) {
                              if(nivel>0){
                                    zoomOutAnimation(this, regiaoSelecionada);                    
                                    sobeNivel();
                                    regiao = regioes[nivel];
                                    regiaoSelecionada = mapa.select(regiao);
                                    setAtributosCamada(regiaoSelecionada.selectAll("path").items);                                    
                              }
                              else {
                                    //alert('Não é mais possivel afastar');
                              }
                              
                        } else {
                              if(nivel<2){
                                    zoomInAnimation(this, regiaoSelecionada);                    
                                    desceNivel();
                                    regiao = regioes[nivel];
                                    regiaoSelecionada = mapa.select(regiao);            
                                    setAtributosCamada(regiaoSelecionada.selectAll("path").items);                                                            
                              }
                              else {
                                    //alert('Não é mais possivel aproximar');
                              }
                        }
                  }
            );
      });
}

// Adiciona efeito de hover em um path do SVG
function setHovers(elemento) {
      if (regioes[nivel]!='#Municipios'){
            setAtributosRegiao(elemento,'#EEDDB3','0.2');
            elemento.hover(
                  () => {
                        fillTooltipData(elemento);
                        setAtributosRegiao(elemento,'red','0.2');
                  },
                  () => setAtributosRegiao(elemento,'#EEDDB3','0')
            )
      } else {
            elemento.hover(
                  () => {
                        fillTooltipData(elemento);
                        setOpacidadeMunicipio(elemento,'0.9')
                  },
                  () => setOpacidadeMunicipio(elemento,'1')
      )}
}

// Controla o Zoom In em um path do svg
function zoomInAnimation(path, regiaoSelecionada) {
      if (regioes[nivel]!='#Municipios'){
            setAtributosRegiao(path,'#EEDDB3','0.2');
            limparEventos(path);
      }
      path.animateSvgFocus(1000, mina.linear, limparRegiao(regiaoSelecionada));
}

// Controla o Zoom Out em um path do svg
function zoomOutAnimation(path, regiaoSelecionada) {

      pathPai = mapa.select('#'+getPath(path));
      limparEventos(path);

      pathPai.animateSvgFocus(1000, mina.linear);
      limparRegiao(regiaoSelecionada);
}

// Limpa um path do SVG
function limparRegiao(regiaoSelecionada) {
      if (regioes[nivel]!='#Municipios'){
            $.each(regiaoSelecionada.selectAll("path").items, function () {
                  limparEventos(this);
                  this.attr({ 'fill': 'none'});
            });
      } else {
            $.each(regiaoSelecionada.selectAll("path").items, function () {
                  limparEventos(this);
                  this.attr({ 'fill-opacity': '1'});
            });
      }
}

// Limpa os eventos de um path do SVG
function limparEventos(elemento) {
      elemento.unhover();
      elemento.unclick();
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

// Direciona o preenchimento do tooltip
function fillTooltipData(path) {
      if (regioes[nivel]=='#Municipios'){
            tooltipMunicipio(path);
      } else if (regioes[nivel]=='#Microrregioes'){
            tooltipMicrorregiao(path);
      } else {
            tooltipMesorregiao(path);
      }
      
      $(classes[nivel]).bind('contextmenu', function () {
            $('#modal')
                  .modal()
                  .text('Dados de ' + descricaoRegiao[nivel] 
                        + ' '
                        + jsonResponse.nome
                        + ' de ID '+ jsonResponse.id + ':');
                        return false;
      });
}

// Adiciona tooltip em um município
function tooltipMunicipio(path) {
      let slicedId = path.attr('id').slice(4, 11);
      let municipio = estadoJson.municipios.find(resultado => resultado.id == slicedId);
      let string = '<title>Município: ' + municipio.nome
      + '&#10;Microrregião: ' + municipio.microrregiao.nome 
      + '&#10;Mesorregião: ' + municipio.microrregiao.mesorregiao.nome
      + '</title>';
      path.append(Snap.parse(string)); 
      jsonResponse = municipio;
}

// Adiciona tooltip em uma microrregião
function tooltipMicrorregiao(path) {
      let slicedId = path.attr('id').slice(4, 9);
      let micro = estadoJson.microrregioes.find(resultado => resultado.id == slicedId);
      let string = '<title>Microrregião: ' + micro.nome
      + '&#10;Mesorregião: ' + micro.mesorregiao.nome
      + '</title>';
      path.append(Snap.parse(string)); 
      jsonResponse = micro;
}

// Adiciona tooltip em uma mesorregião
function tooltipMesorregiao(path) {
      let slicedId = path.attr('id').slice(4, 8);
      let meso = estadoJson.mesorregioes.find(resultado => resultado.id == slicedId);
      let string = '<title>Mesorregião: ' + meso.nome
      + '</title>';
      path.append(Snap.parse(string)); 
      jsonResponse = meso;
}

// Busca o path acima do que foi clicado
function getPath(path) {
      let slicedId;
      let pai;

      if (regioes[nivel]=='#Municipios') {
            slicedId = path.attr('id').slice(4, 11);
            let municipio = estadoJson.municipios.find(resultado => resultado.id == slicedId);
            pai = 'mes_' + municipio.microrregiao.mesorregiao.id;
      } else if (regioes[nivel]=='#Microrregioes') {
            pai='Terreno';
      } 

      return pai;
}

// Seta a opacidade do município, para efeitos de hover
function setOpacidadeMunicipio(elemento, opacidade) {
      elemento.attr({ 'fill-opacity': opacidade});
}

// Seta cor e opacidade das regiões, por efeitos de hover
function setAtributosRegiao(elemento, cor ,opacidade) {
      elemento.attr({ 'fill': cor, 'fill-opacity':opacidade })
}


// Preenche com uma cor passada o município com o id passado 
function colorirMunicipio(idMunicipio, cor) {
      municipio = "#mun_"+idMunicipio;
      $(municipio).attr({ 'fill': cor, 'fill-opacity': 1 });
}

$(document).on({
      keydown: function (e) {
            if (e.isComposing || e.keyCode === 90) {
                  zPressionado = true;
            }
      },
      keyup: function (e) {
            if (e.isComposing || e.keyCode === 90) {
                  zPressionado = false;
            }
      }
});