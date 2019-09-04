package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Microrregiao {

	public int ID;
	public String NOME_MICRORREGIAO;
	public int ID_MESO;
	public int POPULACAO;
	public int VALOR;
	public ArrayList<String> CATEGORIA = new ArrayList<String>();
	public ArrayList<String> ATRIBUTOS = new ArrayList<String>();
	public ArrayList<Integer> VALORES = new ArrayList<Integer>();
	
        public HashMap<String, ArrayList<Integer>> valoresEvolucao = new HashMap<String, ArrayList<Integer>>();
        
    public Microrregiao(int id, String nome_micro, int id_meso, int populacao){
    	ID = id;
      	NOME_MICRORREGIAO = nome_micro;
      	ID_MESO = id_meso;
        POPULACAO = populacao;	
    }
    
    public void addAtributo(String nome, int valor) {
    	ATRIBUTOS.add(nome);
    	VALORES.add(valor);
    }
    
    public void addCategoria(String texto) {
    	CATEGORIA.add(texto);
    }
    
    public void addValoresEvolucao(String periodo, ArrayList<Integer> valores) {
    	valoresEvolucao.put(periodo, valores);
    }
    
    public void calculaAtributoAPartirDosMunicipios(HashMap<Integer, Municipio> municipios, 
                                                    int indexAtributo, String nomeAtributo) {
    	int valorAtributo = 0;
    	 
    	Set<Integer> codigosIBGE = municipios.keySet();
    	for (Integer codigo : codigosIBGE){
    		if(codigo != null) {
                    Municipio mun = municipios.get(codigo);
                    if (mun.ID_MICRO == ID)
                        valorAtributo += mun.VALORES.get(indexAtributo);
    		}
    			
    	}
    	addAtributo(nomeAtributo, valorAtributo);
    }
    
    public void calculaCategoriaAPartirDosMunicipios(HashMap<Integer, Municipio> municipios, 
                                                        int indexAtributo, String nomeAtributo) {
    	int valorAtributo = 0;
    	 
    	Set<Integer> codigosIBGE = municipios.keySet();
    	for (Integer codigo : codigosIBGE)
    	{
    		if(codigo != null) {
    			Municipio mun = municipios.get(codigo);
    			if (mun.ID_MICRO == ID)
    				valorAtributo += mun.VALORES.get(indexAtributo);
    		}
    			
    	}
    	addAtributo(nomeAtributo, valorAtributo);
    }

	public void atualizaAtributos(ArrayList<Integer> vals) {
		if (ATRIBUTOS.size() == 0) inicializaAtributosValoresCategoria(vals.size());
		
		for (int i = 0; i < vals.size(); i++) {
			Integer valor = VALORES.get(i);
			valor = valor + vals.get(i);
			VALORES.set(i, valor);
		}
			
	}

	private void inicializaAtributosValoresCategoria(int sizeAtributos) {
		this.ATRIBUTOS = Estado.ATRIBUTOS;
                this.CATEGORIA = Estado.CATEGORIA;
		for (int i = 0; i < sizeAtributos; i++) //inicializa os valores com zero 
			VALORES.add(0);
	}

}
