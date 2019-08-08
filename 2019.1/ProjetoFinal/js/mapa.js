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
      
      setAtributos(selected);
}

function setAtributos(selected){
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
                                    setAtributos(regiaoSelecionada.selectAll("path").items);                                    
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
                                    setAtributos(regiaoSelecionada.selectAll("path").items);                                                            
                              }
                              else {
                                    alert('Não é mais possivel aproximar');
                              }
                        }
                  }
            );
      });
}

function setHovers(el) {
      if (regioes[nivel]!='#Municipios'){
            el.attr({ 'fill': '#EEDDB3', 'fill-opacity':'0.2' });
            el.hover(
                  () => {
                        fillTooltipData(el);
                        el.attr({ 'fill': 'red', 'fill-opacity':'0.2' });
                  }
                  ,
                  () => el.attr({ 'fill': '#EEDDB3', 'fill-opacity':'0.0' })
            )
      } else {
            el.hover(
                  () => {
                        fillTooltipData(el);
                        el.attr({ 'fill': 'red'})
                  }
                  ,
                  () => el.attr({ 'fill': '#EEDDB3'})
      )}
}


function zoomInAnimation(path, regiaoSelecionada) {
      if (regioes[nivel]!='#Municipios'){
            path.attr({ 'fill': 'red', 'fill-opacity':'0.2' });
            limparEventos(path);
      }
      path.animateSvgFocus(1000, mina.linear, limparRegiao(regiaoSelecionada));
}

function zoomOutAnimation(path, regiaoSelecionada) {

      pathPai = mapa.select('#'+getPath(path));
      limparEventos(path);

      pathPai.animateSvgFocus(1000, mina.linear);
      limparRegiao(regiaoSelecionada);
}

function limparRegiao(regiaoSelecionada) {
      if (regioes[nivel]!='#Municipios'){
            $.each(regiaoSelecionada.selectAll("path").items, function () {
                  limparEventos(this);
                  this.attr({ 'fill': 'none'});
            });
      } else {
            $.each(regiaoSelecionada.selectAll("path").items, function () {
                  limparEventos(this);
                  this.attr({ 'fill': '#EEDDB3'});
            });
      }
}

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

function tooltipMesorregiao(path) {
      urlPath = urls[nivel];
      let slicedId = path.attr('id').slice(4, 8);
                              let jsondata = fetch(urlPath + slicedId)
                                    .then(res => res.json())
                                    .then(data => jsonResponse = data)
                                    .then(() => path.append(Snap.parse('<title>Mesorregião: ' + jsonResponse.nome
                                                + '</title>')));
}

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

// Preenche com uma cor passada o município com o id passado 
function colorirMunicipio(idMunicipio, cor) {
      municipio = "#mun_"+idMunicipio;
      $(municipio).attr({ 'fill': cor, 'fill-opacity': 1 });
}