# Para rodar: 
- para rodar, utilize um conteiner web
- utilizando o node.js e Considerando que você está na pasta ProjetoFinal, execute: 
> node node/index.js
- os arquivos de entrada estão em jsons.
	- uf_geral_periodo.json (1..n), corresponde aos arquivos gerais utilizados para o mapa.
	- uf_evolucao.json, utilizado pelas visões de evolução que são carregadas a partir do mapa

# Gerar os jsons
- ver pasta preprocessamento, fora do projeto. ela contem diferentes soluções para gerar o json	

# Uso
- Mecanismos de interação
	- zoom in = clique direito
	- zoom out = clique direito + z
	- menupop up = clique esquerdo (as três primeiras opcoes estao ok) 
	- Componente Período ainda nao está funcionando, dessa forma no - mapa vc só ver os dados do ultimo período. A evolucao aparece nas visões abaixo..	
- o arquivo js/mapa.js tem as configurações iniciais do app

# To Do List
- organizar os arquivos. Principalmente os arquivos js. Estão espalhados. js/mapa.js, js/search.js, script/script.js script/sketch.js, 
- ver questào do sinc/asinc. O mapa da bahia as vezes nao é carregado adequadamente. é preciso ajsutar a ordem de chamada das funções
- corrigir o componente período
- adicionar feature para formatar o dado quando ele for real (moeda). Isso deve ser uma informação em config.json
- adição de novas visualizações multivariadas

# Sugestões
- Adicionar Console.error e warn para eventuais erros e afins
- Documentação de First Steps/First Guide/First Time da aplicação
- Documentação dos principais scripts (Mapa.js, Search.js, Script.js e sketch.js)

# Passo a passo de como modificar SVGs da Wikipedia para a aplicação

## Municipios

No grupo `<g id="Municpios">`:
- Corrigir o id do grupo de "Municpios" para "Municipios"
- Para todos os paths, remover a classe de fill, e adicionar classe **mun**. Ex.: `class="fil3 str2"` -> `class="mun str2"`
- Cada path terá duas classes: uma responsável pelo preenchimento e seleção, a **mun**, e outra responsável pelo contorno, a str. Ex.: `class="mun str5"`
- Para todos os paths, adicionar um atributo fill com a cor padrão desejada. Ex.: `fill="#EEDDB3"`

## Microrregiões

No grupo `<g id="Microrregies">`:
- Corrigir o id do grupo de "Microrregies" para "Microrregioes"
- Modificar as ids de todos os paths, para começar com "mic_", e não "micro_". Ex.: `id="micro_290732"` -> `id="mic_290732"`
- Remover o antepenúltimo digito dos ids de todos os paths. São dígitos utilizados somente no SVG, e não existem nos dados do IBGE. Ex.: `id="mic_290519` -> `id="mic_29019"`
- Para todos os paths, remover a classe de fill, e adicionar classe **micreg**. Ex.: `class="fil3 str5"` -> `class="micreg str5"`
- Cada path terá duas classes: uma responsável pelo preenchimento e seleção, a **micreg**, e outra responsável pelo contorno, a str. Ex.: `class="micreg str5"`
- Para todos os path, adicionar um atributo fill com a cor padrão desejada. Ex.: `fill="#EEDDB3"`
- Para modificar o contorno das microrregiões, por exemplo, deixá-los vermelho, adicionar "stroke:red" no style da classe responsável pelo contorno. Nos SVGs da wikipedia, os styles são encontrados próximos ao início do arquivo, com a tag `<style type="text/css">`. Ex.: `.str5 {stroke:red;stroke-width:76;stroke-linecap:round;stroke-linejoin:round}`

## Mesorregiões

No grupo `<g id="Mesorregies">`:
- Corrigir o id do grupo de "Mesorregies" para "Mesorregioes"
- Modificar as ids de todos os paths, para começar com "mes_", e não "meso_". Ex.: `id="meso_2906"` -> `id="mes_2906"`
- Para todos os paths, remover a classe de fill, e adicionar classe **mesoreg**. Ex.: `class="fil3 str7"` -> `class="mesoreg str7"`
- Cada path terá duas classes: uma responsável pelo preenchimento e seleção, a **mesoreg**, e outra responsável pelo contorno, a str. Ex.: `class="mesoreg str5"`
- Para todos os path, adicionar um atributo fill com a cor padrão desejada. Ex.: `fill="#EEDDB3"`

## Outras modificações

Outros grupos podem ser removidos do SVG para deixar a representação mais limpa, aparecendo somente o mapa sem contorno e informações dos estados vizinhos e minimapa.

- O grupo `<g id="Divises dos vizinhos">` pode ser completamente removido.
- No grupo `<g id="Terreno">` pode ser deixado apenas o path com o estado sendo representado. Ex.: com o `id="uf_29"`, caso esteja representado o mapa da Bahia.
- No grupo `<g id="Divises estaduais">` pode ser deixado apenas o path com o estado sendo representado. Ex.: com o `id="uf_29"`, caso esteja representado o mapa da Bahia.
- O grupo `<g id="Minimapa">` pode ser completamente removido.
- O grupo `<g id="Contorno">` pode ser completamente removido.