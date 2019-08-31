- Projeto java para geração do arquivo json geral do estado.
- projeto feito em Eclipse, mas é possível rodar via linha de comando ou em outra IDE de seu interesse.
- há dependência da internet, pois acessa serviços do IBGE
- O arquivo principal é o EstadoJsonGenerator, ele usa as duas outras classes para geração dos arquivos gerais e do de evolução

- EstadoJsonGeral.java - responsável por gerar os jsons gerais do estado.
	- ele inicializa os dados das meso, micro e municipios a partir do IBGE.
	- no caso dos municipios, foi colocado um arquivo local, pois dava algum erro intermitente quando era via http
	- o ponto chave é o cadastro de atributos. Isso deve ser feito seguindo o modelo de população
		- para adicionar um atributo, crie um arquivo que tenha codigoIbge do município e o valor do atributo
		- atualize o método addAtributosMuncipios e os métodos correspondentes
	- todo o cálculo de atributos das mesos e micros são atualizados na hora que é montado o estado
	- gera um arquivo geral por vez

Limitações
- ainda não preenche as categoria.
- definir os percentis para mudar a estratégia de colocaração

- EstadoJsonEvoluca.java - responsável por gerar os json de evolução.
	- ele inicializa os dados das meso, micro e municipios a partir do IBGE.
	- no caso dos municipios, foi colocado um arquivo local, pois dava algum erro intermitente quando era via http
	- o ponto chave é o cadastro da evolução dos atributos. 
		- gera a partir dos arquivos gerais de estado que existe
		- monte o array de períodos "periodos[]" correspondente aos arquivos gerais que você tem na pasta dados
		- monte o array de atributos "atributos[]"

Dica
	- se você tiver os dados em colunas no excel e copiar para um arquivo txt, ele já separa pelo \t. Assim você terá esforco mínimo para procesar os seus dados. Caso esteja em outro padrão, você deve adaptar o código de leitura de arquivos.
	- Veja o exemplo de óbitos. Tem uma planilha excel, com os óbitos por ano. Isso foi copiado para um arquivo txt. É a partir dele que é gerado os arquivos gerais.
	- IMPORTANTE: o código IBGE do munícipio tem que ser o mesmo fornecido pelo site do IBGE. Alguns dados vem com dígitos a menos. Na planilha óbitos tem os códigos dos municípios da Bahia.