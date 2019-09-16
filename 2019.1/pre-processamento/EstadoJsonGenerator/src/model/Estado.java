package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Estado {
	
	public String sigla = "ba";
	public String nome_capital = "Salvador";
	public int codigo_capital = 2927408;

	//min max por período
	public ArrayList<Integer>  min_values_periodo = new ArrayList<Integer>();
	public ArrayList<Integer>  max_values_periodo = new ArrayList<Integer>();

	public HashMap<Integer, Mesorregiao> mesorregioes;
	public HashMap<Integer, Microrregiao> microrregioes;
	public HashMap<Integer, Municipio> municipios;
	
	public ArrayList<String> CATEGORIA = new ArrayList<String>();
	public static ArrayList<String> ATRIBUTOS = new ArrayList<String>();
	
	public HashMap<String, ArrayList<Integer>> valoresAtributos = new HashMap<String, ArrayList<Integer>>();
	
	public HashMap<String, ArrayList<Integer>> percentis = new HashMap<String, ArrayList<Integer>>();
	
	
	//definir max e min na evolução
	public ArrayList<Integer>  mesorregioes_min_values_evolution = new ArrayList<Integer>();
	public ArrayList<Integer>  mesorregioes_max_values_evolution = new ArrayList<Integer>();
	public ArrayList<Integer>  microrregioes_min_values_evolution = new ArrayList<Integer>();
	public ArrayList<Integer>  microrregioes_max_values_evolution = new ArrayList<Integer>();
	public ArrayList<Integer>  municipios_min_values_evolution = new ArrayList<Integer>();
	public ArrayList<Integer>  municipios_max_values_evolution = new ArrayList<Integer>();

	
	
	public Estado(String sigla, HashMap<Integer, Mesorregiao> mesos, HashMap<Integer, Microrregiao> micros,
			HashMap<Integer, Municipio> muns, boolean multiplosPeriodos) {
		this.sigla = sigla;
		mesorregioes = mesos;
		microrregioes = micros;
		municipios = muns;
		
		//colocar os atributos de um municipio no estado
		Collection<Municipio> munsTemp = muns.values();
		Iterator<Municipio> itMun = munsTemp.iterator();
		Municipio munTemp = itMun.next();
		ATRIBUTOS = munTemp.ATRIBUTOS;
		
		
		//inicializa min e max values
		int qtdAtributos = ATRIBUTOS.size();
		
		for (int i = 0; i < qtdAtributos; i++) { 
			min_values_periodo.add(munTemp.VALORES.get(i));
			max_values_periodo.add(munTemp.VALORES.get(i));
			//cria o arraylist para cada um dos atributos para poder definir os percentis
			valoresAtributos.put(ATRIBUTOS.get(i), new ArrayList<Integer>());
			percentis.put(ATRIBUTOS.get(i), new ArrayList<Integer>());
		}	
		
		atualizaAtributosMicros();
		atualizaAtributosMesos();
		definePercentis();
		if (multiplosPeriodos) {//evolucao
			
			/*Collection<Mesorregiao> mesosTemp = mesorregioes.values();
			Iterator<Mesorregiao> itMeso = mesosTemp.iterator();
			Mesorregiao meso = itMeso.next();
			for (int i = 0; i < qtdAtributos; i++) {
				mesorregioes_min_values_evolution.add(meso.VALORES.get(i));
			}*/
			//inicializa atributos min max na evolução
			//mesorregioes_min_values_evolution.add(munTemp.VALORES.get(i));
			//mesorregioes_max_values_evolution.add(munTemp.VALORES.get(i));

			defineMinMaxEvolucao();
		}	
		System.out.println("");
	}

	@SuppressWarnings("unchecked")
	private void definePercentis() {
		int qtdAtributos = ATRIBUTOS.size();
		
		for (int i = 0; i < qtdAtributos; i++) { 
			ArrayList<Integer> atributos = valoresAtributos.get(ATRIBUTOS.get(i));
			atributos.sort(new Comparator() {  
	            public int compare(Object o1, Object o2) {  
	                 Integer c1 = (Integer) o1;  
	                 Integer c2 = (Integer) o2;  
	                 return c1.intValue() - c2.intValue();
	               }
				});
			double index = (atributos.size()/100d)*20;
			percentis.get(ATRIBUTOS.get(i)).add(atributos.get((int)index));
			index = (atributos.size()/100d)*40;
			percentis.get(ATRIBUTOS.get(i)).add(atributos.get((int)index));
			index = (atributos.size()/100d)*60;
			percentis.get(ATRIBUTOS.get(i)).add(atributos.get((int)index));
			index = (atributos.size()/100d)*80;
			percentis.get(ATRIBUTOS.get(i)).add(atributos.get((int)index));
		}		
	}
	
	@SuppressWarnings("unchecked")
	private void defineMinMaxEvolucao() {
		
		Set<Integer> codigosMesos = mesorregioes.keySet();
    	for (Integer codigoMeso : codigosMesos)
    	{
    		Mesorregiao meso = mesorregioes.get(codigoMeso);
    		Set<String> periodos = meso.valoresEvolucao.keySet();
        	for (String periodo : periodos)
        	{	        		
        		ArrayList<Integer> valores =  meso.valoresEvolucao.get(periodo);
        		atualizaMinMaxEvolucaoMeso(valores);
        	}	
    	}
    	
    	Set<Integer> codigosMicros = microrregioes.keySet();
    	for (Integer codigoMicro : codigosMicros)
    	{
    		Microrregiao micro = microrregioes.get(codigoMicro);
    		Set<String> periodos = micro.valoresEvolucao.keySet();
        	for (String periodo : periodos)
        	{	        		
        		ArrayList<Integer> valores =  micro.valoresEvolucao.get(periodo);
        		atualizaMinMaxEvolucaoMicro(valores);
        	}	
    	}
    	
    	
    	Set<Integer> codigosMuns = municipios.keySet();
    	for (Integer codigoMun : codigosMuns)
    	{
    		Municipio mun = municipios.get(codigoMun);
    		Set<String> periodos = mun.valoresEvolucao.keySet();
        	for (String periodo : periodos)
        	{	        		
        		ArrayList<Integer> valores =  mun.valoresEvolucao.get(periodo);
        		atualizaMinMaxEvolucaoMunicipios(valores);
        	}	
    	}
    	
    	System.out.println("");
    	
	}

	private void atualizaMinMaxEvolucaoMeso(ArrayList<Integer> valores){
		int qtdAtributos = valores.size();
		
		boolean arrayVazio = (mesorregioes_min_values_evolution.size() == 0);
		for (int i = 0; i < qtdAtributos; i++) { 
			int currentValue = valores.get(i);
			
			if (arrayVazio) {
				mesorregioes_min_values_evolution.add(currentValue);
				mesorregioes_max_values_evolution.add(currentValue);			
			}else {	
				if (currentValue < mesorregioes_min_values_evolution.get(i))
					mesorregioes_min_values_evolution.set(i, currentValue);
				if (currentValue > mesorregioes_max_values_evolution.get(i))
					mesorregioes_max_values_evolution.set(i, currentValue);
			}	
		}
		
	}
	
	private void atualizaMinMaxEvolucaoMicro(ArrayList<Integer> valores){
		int qtdAtributos = valores.size();
		
		boolean arrayVazio = (microrregioes_min_values_evolution.size() == 0);
		for (int i = 0; i < qtdAtributos; i++) { 
			int currentValue = valores.get(i);
			
			if (arrayVazio) {
				microrregioes_min_values_evolution.add(currentValue);
				microrregioes_max_values_evolution.add(currentValue);			
			}else {	
				if (currentValue < microrregioes_min_values_evolution.get(i))
					microrregioes_min_values_evolution.set(i, currentValue);
				if (currentValue > microrregioes_max_values_evolution.get(i))
					microrregioes_max_values_evolution.set(i, currentValue);
			}	
		}
		
	}
	
	private void atualizaMinMaxEvolucaoMunicipios(ArrayList<Integer> valores){
		int qtdAtributos = valores.size();
		
		boolean arrayVazio = (municipios_min_values_evolution.size() == 0);
		for (int i = 0; i < qtdAtributos; i++) { 
			int currentValue = valores.get(i);
			
			if (arrayVazio) {
				municipios_min_values_evolution.add(currentValue);
				municipios_max_values_evolution.add(currentValue);			
			}else {	
				if (currentValue < municipios_min_values_evolution.get(i))
					municipios_min_values_evolution.set(i, currentValue);
				if (currentValue > municipios_max_values_evolution.get(i))
					municipios_max_values_evolution.set(i, currentValue);
			}	
		}
		
	}
	
	private void atualizaAtributosMesos() {
		Set<Integer> codigosIBGEMicros = microrregioes.keySet();
    	for (Integer codigoMicro : codigosIBGEMicros)
    	{
    		if(codigoMicro != null) {
    			Microrregiao micro = microrregioes.get(codigoMicro);
    			Mesorregiao meso = mesorregioes.get(micro.ID_MESO);
    			meso.atualizaAtributos(micro.VALORES);
    		}
    	}
		
	}

	private void atualizaAtributosMicros() {
		
    	Set<Integer> codigosIBGEMunicipios = municipios.keySet();
    	for (Integer codigoMun : codigosIBGEMunicipios)
    	{
    		if(codigoMun != null) {
    			Municipio mun = municipios.get(codigoMun);
    			atualizaMaxMin_e_Percentis(mun);
    			Microrregiao micro = microrregioes.get(mun.ID_MICRO);
    			micro.atualizaAtributos(mun.VALORES);
    		}
    	}
	}


	private void atualizaMaxMin_e_Percentis(Municipio mun){
		int qtdAtributos = ATRIBUTOS.size();
		
		for (int i = 0; i < qtdAtributos; i++) { 
			int currentValue = mun.VALORES.get(i);
			valoresAtributos.get(ATRIBUTOS.get(i)).add(currentValue);
			if (currentValue < min_values_periodo.get(i))
				min_values_periodo.set(i, currentValue);
			if (currentValue > max_values_periodo.get(i))
				max_values_periodo.set(i, currentValue);
		}
		
	}
	
}

/*
 * Set<Integer> codigosIBGEMicro = microrregioes.keySet();
		for (Integer codigoMicro : codigosIBGEMicro)
    	{
    	
    	*/
