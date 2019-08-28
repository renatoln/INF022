package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Estado {
	
	public String sigla = "ba";
	public String nome_capital = "Salvador";
	public int codigo_capital = 2927408;
	public ArrayList<Integer>  min_values = new ArrayList<Integer>();
	public ArrayList<Integer>  max_values = new ArrayList<Integer>();
	public String periodos[] = {"2019-01", "2019-02", "2019-03", "2019-04", "2019-05", "2019-06"};
	
	public HashMap<Integer, Mesorregiao> mesorregioes;
	public HashMap<Integer, Microrregiao> microrregioes;
	public HashMap<Integer, Municipio> municipios;
	

	public ArrayList<String> CATEGORIA = new ArrayList<String>();
	public static ArrayList<String> ATRIBUTOS = new ArrayList<String>();
	public ArrayList<Integer> VALORES = new ArrayList<Integer>();
	
	public Estado(String sigla, HashMap<Integer, Mesorregiao> mesos, HashMap<Integer, Microrregiao> micros,
			HashMap<Integer, Municipio> muns) {
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
			min_values.add(munTemp.VALORES.get(i));
			max_values.add(munTemp.VALORES.get(i));
		}	
		
		atualizaAtributosMicros();
		atualizaAtributosMesos();
		System.out.println("");
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
    			atualizaMaxMin(mun);
    			Microrregiao micro = microrregioes.get(mun.ID_MICRO);
    			micro.atualizaAtributos(mun.VALORES);
    		}
    	}
	}
	
	private void atualizaMaxMin(Municipio mun){
		int qtdAtributos = ATRIBUTOS.size();
		
		for (int i = 0; i < qtdAtributos; i++) { 
			int currentValue = mun.VALORES.get(i);
			if (currentValue < min_values.get(i))
				min_values.set(i, currentValue);
			if (currentValue > max_values.get(i))
				max_values.set(i, currentValue);
		}
		
	}
	
}

/*
 * Set<Integer> codigosIBGEMicro = microrregioes.keySet();
		for (Integer codigoMicro : codigosIBGEMicro)
    	{
    	
    	*/
