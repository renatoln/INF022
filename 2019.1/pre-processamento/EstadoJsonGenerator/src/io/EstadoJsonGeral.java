package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


import model.Estado;
import model.Mesorregiao;
import model.Microrregiao;
import model.Municipio;

public class EstadoJsonGeral {
	HashMap<Integer, Integer> populacaoMap = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> pibMap = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> obitosMap = new HashMap<Integer, Integer>();
	EstadoJsonGenerator myEstadoJsonGenerator; 
	
			
	public EstadoJsonGeral(EstadoJsonGenerator estadoJsonGeral) {
		myEstadoJsonGenerator = estadoJsonGeral;
		start();
	}		
	
	private void start() {
		HashMap<Integer, Mesorregiao> mesos = myEstadoJsonGenerator.getMesorregioes();
		HashMap<Integer, Microrregiao> micros = myEstadoJsonGenerator.getMicrorregioes();
		HashMap<Integer, Municipio> municipios = myEstadoJsonGenerator.getMunicipios();
		//printMunicipios(municipios);
		addAtributosMunicipios(municipios);
		printPopulacaoMunicipios();
		//Estado estado = new Estado(myEstadoJsonGenerator.codigoEstado, mesos, micros, municipios);
		
		//gerarJsonGeralEstado(estado);
		
	}
	
	private void printMunicipios(HashMap<Integer, Municipio> municipios) {
		Set<Integer> codigosIBGEMunicipios = municipios.keySet();
    	for (Integer codigoMun : codigosIBGEMunicipios)
    	{
    		if(codigoMun != null) {
    			Municipio mun = municipios.get(codigoMun);
    			System.out.println("municipios.put(\""+mun.NOME_MUNICIPIO+"\","+mun.ID+");");
    		}
    	}	
		
	}
	
	private void printPopulacaoMunicipios() {
		Set<Integer> populacaoMunicipios = populacaoMap.keySet();
    	for (Integer codigoMun : populacaoMunicipios)
    	{
    		if(codigoMun != null && codigoMun.toString().startsWith("29")) {
    			int populacao = populacaoMap.get(codigoMun);
    			System.out.println(codigoMun + "\t" + populacao);
    		}
    	}	
		
	}

	private void addAtributosMunicipios(HashMap<Integer, Municipio> municipios) {
		//carregar populacao a partir de arquivo
		importPopulacaoPorMunicipio();
		//carregar pib a partir de arquivo
		importPibPorMunicipio();
		//carregar obitos a partir de arquivo
		importObitosPorMunicipio();
		
		Set<Integer> codigosIBGEMunicipios = municipios.keySet();
    	for (Integer codigoMun : codigosIBGEMunicipios)
    	{
    		if(codigoMun != null) {
    			Municipio mun = municipios.get(codigoMun);
    			int populacao = populacaoMap.get(codigoMun);
    			int pib = pibMap.get(codigoMun);
    			int obitos = obitosMap.get(codigoMun);
    			mun.addAtributo("População", populacao);
    			mun.addAtributo("PIB", pib);
    			mun.addAtributo("PIB / pop", pib/populacao);
    			mun.addAtributo("Óbitos", obitos);
    		}
    	}
		
	}
	
	
	
	public void importPopulacaoPorMunicipio(){
		try {
			File file = new File(myEstadoJsonGenerator.urlFolderDados+"municipio-populacao.txt");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			int codigoIBGE;
			int populacao;
			
			String linha = br.readLine(); //pular a primeira linha
			
			while (br.ready()){ 
				linha = br.readLine(); 
				String[] fields = linha.split("\t");
				
				codigoIBGE = Integer.parseInt(fields[1]);
				
				populacao = Integer.parseInt(fields[3]);
				
				populacaoMap.put(codigoIBGE, populacao);
				
			} 
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void importPibPorMunicipio(){
		try {
			File file = new File(myEstadoJsonGenerator.urlFolderDados+"municipio-pib.txt");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			int codigoIBGE;
			int pib;
			
			String linha = br.readLine(); //pular a primeira linha
			
			while (br.ready()){ 
				linha = br.readLine(); 
				String[] fields = linha.split("\t");
				
				codigoIBGE = Integer.parseInt(fields[3]);
				
				pib = Integer.parseInt(fields[2]);
				
				pibMap.put(codigoIBGE, pib);
				
			} 
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void importObitosPorMunicipio(){
		try {
			File file = new File(myEstadoJsonGenerator.urlFolderDados+"municipio-obitos-2017-2013.txt");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			int codigoIBGE;
			int obitos;
			
			String linha = br.readLine(); //pular a primeira linha
			
			while (br.ready()){ 
				linha = br.readLine(); 
				String[] fields = linha.split("\t");
				
				codigoIBGE = Integer.parseInt(fields[0]);
				
				obitos = Integer.parseInt(fields[5]); //muda aqui para mudar o ano
				
				obitosMap.put(codigoIBGE, obitos);
				
			} 
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void gerarJsonGeralEstado(Estado estado) {
		String fileName = myEstadoJsonGenerator.getNomeJsonGeral(myEstadoJsonGenerator.periodoAtual);
		
		myEstadoJsonGenerator.createFile(fileName, "{\n\t\"METADADOS\":{");

		
		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(estado.min_values, "MIN_Valores", true));
		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(estado.max_values, "MAX_Valores", true));
		
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t\t\"PERCENTIS\":[");
		for (int i = 0; i < estado.percentis.size(); i++) {
			String atributo = estado.ATRIBUTOS.get(i);
			myEstadoJsonGenerator.appendTexto(fileName, "\n\t\t{ \"NOME\": \""+ atributo+"\",");
			myEstadoJsonGenerator.appendTexto(fileName, list_int_values(estado.percentis.get(atributo), "VALORES", false));
			myEstadoJsonGenerator.appendTexto(fileName, "}");
			if (i < estado.percentis.size() - 1)
				myEstadoJsonGenerator.appendTexto(fileName, ",");
		}
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t\t]\n");
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t},\n");
		
		//mesorregioes
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t\"MESORREGIOES\":[\n");
		
		String textoAppend;
		Collection<Mesorregiao> mesorregioes = estado.mesorregioes.values();
		int size = mesorregioes.size();
		int i = 0;
    	for (Mesorregiao meso : mesorregioes)
    	{
    		textoAppend = "  {\n" + 
					"    \"ID\": " + meso.ID + " ,\n" + 
					"    \"NOME_MESORREGIAO\": \"" + meso.NOME_MESORREGIAO + "\"," +
					list_string_values(meso.CATEGORIA, "CATEGORIA", true)+
					list_string_values(meso.ATRIBUTOS, "ATRIBUTOS", true)+
					list_int_values(meso.VALORES, "VALORES", false)+"\n"+
					"  }";
    		
			if (i < size - 1) textoAppend += ",";
			
			textoAppend += "\n";
			
			myEstadoJsonGenerator.appendTexto(fileName, textoAppend);
			i++;
    	}
		
		
    	myEstadoJsonGenerator.appendTexto(fileName, "],");
		
		//microrregioes
    	myEstadoJsonGenerator.appendTexto(fileName, "\n\t\"MICRORREGIOES\":[\n");
		
		Collection<Microrregiao> microrregioes = estado.microrregioes.values();
		size = microrregioes.size();
		i = 0;
    	for (Microrregiao micro : microrregioes)
    	{
    		textoAppend = "  {\n" + 
					"    \"ID\": " + micro.ID + ",\n" + 
					"    \"NOME_MICRORREGIAO\": \"" + micro.NOME_MICRORREGIAO + "\"," +
					list_string_values(micro.CATEGORIA, "CATEGORIA", true)+
					list_string_values(micro.ATRIBUTOS, "ATRIBUTOS", true)+
					list_int_values(micro.VALORES, "VALORES", true)+"\n"+
					"    \"ID_MESO\": " + micro.ID_MESO + " \n" +
					"  }";
    		
			if (i < size - 1) textoAppend += ",";
			
			textoAppend += "\n";
			
			myEstadoJsonGenerator.appendTexto(fileName, textoAppend);
			i++;
    	}
		
		
    	myEstadoJsonGenerator.appendTexto(fileName, "],");
		
		//municipios
    	myEstadoJsonGenerator.appendTexto(fileName, "\n\t\"MUNICIPIOS\":[\n");
		
		Collection<Municipio> municipios = estado.municipios.values();
		size = municipios.size();
		i = 0;
    	for (Municipio mun : municipios)
    	{
    		textoAppend = "  {\n" + 
					"    \"ID\": " + mun.ID + ",\n" + 
					"    \"NOME_MUNICIPIO\": \"" + mun.NOME_MUNICIPIO + "\"," +
					list_string_values(mun.CATEGORIA, "CATEGORIA", true)+
					list_string_values(mun.ATRIBUTOS, "ATRIBUTOS", true)+
					list_int_values(mun.VALORES, "VALORES", true)+"\n"+
					"    \"ID_MESO\": " + mun.ID_MESO + ", \n" +
					"    \"ID_MICRO\": " + mun.ID_MICRO + " \n" +
					"  }";
    		
			if (i < size - 1) textoAppend += ",";
			
			textoAppend += "\n";
			
			myEstadoJsonGenerator.appendTexto(fileName, textoAppend);
			i++;
    	}
		
		
    	myEstadoJsonGenerator.appendTexto(fileName, "]\n");
		myEstadoJsonGenerator.appendTexto(fileName, "}");
		
		
	}
	
	private String list_int_values(ArrayList<Integer> valores, String s, boolean virgulaFinal) {
		
		String str = "\n\t\t\""+ s+ "\"";
		
		str += ": [";
		for (int i = 0; i < valores.size(); i++) {
			str += valores.get(i);
			if (i < valores.size() - 1)
				str += ", ";
		}
		str += "]";
		
		if (virgulaFinal) str += ",";
		
		return str;
	}
	
	private String list_string_values(ArrayList<String> valores, String s, boolean virgulaFinal) {
		
		String str = "\n\t\t\""+ s + "\"";
		
		str += ": [";
		for (int i = 0; i < valores.size(); i++) {
			str += "\""+valores.get(i)+"\"";
			if (i < valores.size() - 1)
				str += ", ";
		}
		str += "]";
		
		if (virgulaFinal) str += ",";
		
		return str;
	}
	
}
