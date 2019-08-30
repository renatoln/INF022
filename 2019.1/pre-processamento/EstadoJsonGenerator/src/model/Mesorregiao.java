package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Mesorregiao {
	
	public int ID;
	public String NOME_MESORREGIAO;
	public int POPULACAO;
	public int VALOR;
	public ArrayList<String> CATEGORIA = new ArrayList<String>();
	public ArrayList<String> ATRIBUTOS = new ArrayList<String>();
	public ArrayList<Integer> VALORES = new ArrayList<Integer>();
	
	public HashMap<String, ArrayList<Integer>> valoresEvolucao = new HashMap<String, ArrayList<Integer>>();
	
    public Mesorregiao(int id, String nome_meso, int populacao){
    	ID = id;
      	NOME_MESORREGIAO = nome_meso;
        POPULACAO = populacao;
    	
    }
    
    public void addAtributo(String nome, int valor) {
    	ATRIBUTOS.add(nome);
    	VALORES.add(valor);
    }
    
    public void addValoresEvolucao(String periodo, ArrayList<Integer> valores) {
    	valoresEvolucao.put(periodo, valores);
    	
    }
    public void calculaAtributoAPartirDasMicroregioes(HashMap<Integer, Microrregiao> microrregioes, int indexAtributo, String nomeAtributo) {
    	int valorAtributo = 0;
    	 
    	Set<Integer> codigosIBGE = microrregioes.keySet();
    	for (Integer codigo : codigosIBGE)
    	{
    		if(codigo != null) {
    			Microrregiao micro = microrregioes.get(codigo);
    			if (micro.ID_MESO == ID)
    				valorAtributo += micro.VALORES.get(indexAtributo);
    		}
    			
    	}
    	addAtributo(nomeAtributo, valorAtributo);
    }
    
    public void atualizaAtributos(ArrayList<Integer> vals) {
		if (ATRIBUTOS.size() == 0) inicializaAtributosEValores(vals.size());
		
		for (int i = 0; i < vals.size(); i++) {
			Integer valor = VALORES.get(i);
			valor = valor + vals.get(i);
			VALORES.set(i, valor);
		}
			
	}

	private void inicializaAtributosEValores(int sizeAtributos) {
		this.ATRIBUTOS = Estado.ATRIBUTOS;
		for (int i = 0; i < sizeAtributos; i++) //inicializa os valores com zero 
			VALORES.add(0);
	}
}
