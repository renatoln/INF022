let cidades = [];
var mapa = Snap('#mapa'); // Passa ao Snap o id da tag <svg> de trabalho
var svg = 'maps/bahia.svg';
var descricaoRegiao = ['mesorregião', 'microrregião', 'município'];
var regioes = ['#Mesorregioes','#Microrregioes','#Municipios'];
var urls = ["https://servicodados.ibge.gov.br/api/v1/localidades/mesorregioes/",
            "https://servicodados.ibge.gov.br/api/v1/localidades/microrregioes/",
            "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/"];
var classes = ['.mesoreg','.micreg','.mun'];
var urlPath;
var nivel = 0;
var init = false;
var jsonResponse;
var regiao = regioes[nivel];
var regiaoSelecionada;

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
      
      cidades = mapa.select("#Municipios").selectAll("path").items;
      //cidades[0].node.attributes.fill.value = "#dddddd";
}

// Seta os atributos da camada atual do mapa
function setAtributosCamada(selected){
      $.each(selected, function () {
            setHovers(this);
            this.click(
                  (e) => {
                        if (e.ctrlKey) {
                              if(nivel>0){
                                    zoomOutAnimation(this, regiaoSelecionada);                    
                                    sobeNivel();
                                    regiao = regioes[nivel];
                                    regiaoSelecionada = mapa.select(regiao);
                                    setAtributosCamada(regiaoSelecionada.selectAll("path").items);                                    
                              }
                              else {
                                    alert('Não é mais possivel afastar');
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
                                    alert('Não é mais possivel aproximar');
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
      urlPath = urls[nivel];
      let slicedId = path.attr('id').slice(4, 11);
                              let jsondata = fetch(urlPath + slicedId)
                                    .then(res => res.json())
                                    .then(data => jsonResponse = data)
                                    .then(() => path.append(Snap.parse('<title>Municipio: ' + jsonResponse.nome
                                          + '&#013Microrregião: ' + jsonResponse.microrregiao.nome
                                          + '&#013Mesorregião: ' + jsonResponse.microrregiao.mesorregiao.nome
                                          + '</title>')));
}

// Adiciona tooltip em uma microrregião
function tooltipMicrorregiao(path) {
      urlPath = urls[nivel];
      let slicedId = path.attr('id').slice(4, 9);
                              let jsondata = fetch(urlPath + slicedId)
                                    .then(res => res.json())
                                    .then(data => jsonResponse = data)
                                    .then(() => path.append(Snap.parse('<title>Microrregião: ' + jsonResponse.nome
                                          + '&#013Mesorregião: ' + jsonResponse.mesorregiao.nome
                                          + '</title>')));
      response = jsonResponse;

}

// Adiciona tooltip em uma mesorregião
function tooltipMesorregiao(path) {
      urlPath = urls[nivel];
      let slicedId = path.attr('id').slice(4, 8);
                              let jsondata = fetch(urlPath + slicedId)
                                    .then(res => res.json())
                                    .then(data => jsonResponse = data)
                                    .then(() => path.append(Snap.parse('<title>Mesorregião: ' + jsonResponse.nome
                                                + '</title>')));
}

// Busca o path acima do que foi clicado
function getPath(path) {
      urlPath = urls[nivel];
      let jsondata;
      let slicedId;
      let pai;

      if (regioes[nivel]=='#Municipios'){
            slicedId = path.attr('id').slice(4, 9);
            jsondata = fetch(urlPath + slicedId)
                                    .then(res => res.json())
                                    .then(data => jsonResponse = data)
                                    .then((pai = 'mes_'+jsonResponse.microrregiao.mesorregiao.id))
      } else if (regioes[nivel]=='#Microrregioes'){
            pai='Terreno';
      } else {
            //TO DO: adicionar mais um nível
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