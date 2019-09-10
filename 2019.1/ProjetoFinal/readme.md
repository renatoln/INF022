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
