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
	HashMap<Integer, Integer> escolasMap = new HashMap<Integer, Integer>();
	EstadoJsonGenerator myEstadoJsonGenerator; 
	String nomeArquivo;
			
	public EstadoJsonGeral(EstadoJsonGenerator estadoJsonGeral) {
		myEstadoJsonGenerator = estadoJsonGeral;
		start();
	}		
	
	private void start() {
		HashMap<Integer, Mesorregiao> mesos = myEstadoJsonGenerator.getMesorregioes();
		HashMap<Integer, Microrregiao> micros = myEstadoJsonGenerator.getMicrorregioes();
		HashMap<Integer, Municipio> municipios = myEstadoJsonGenerator.getMunicipios();
		
		addAtributosMunicipios(municipios);
		
		Estado estado = new Estado(myEstadoJsonGenerator.codigoEstado, mesos, micros, municipios);
		gerarJsonGeralEstado(estado);
		
	}
	
	private void addAtributosMunicipios(HashMap<Integer, Municipio> municipios) {
		//carregar populacao a partir de arquivo
		importValores();
		
		Set<Integer> codigosIBGEMunicipios = municipios.keySet();
    	for (Integer codigoMun : codigosIBGEMunicipios)
    	{
    		if(codigoMun != null) {
    			Municipio mun = municipios.get(codigoMun);
    			int populacao = populacaoMap.get(codigoMun);
    			int pib = pibMap.get(codigoMun);
    			int escolas = escolasMap.get(codigoMun);
    			mun.addAtributo("População", populacao);
    			mun.addAtributo("PIB", pib);
    			mun.addAtributo("PIB / pop", pib/populacao);
    			mun.addAtributo("Escolas", escolas);
                }
    	}
        }
        
	public void importValores(){
		try {
			File file = new File(myEstadoJsonGenerator.urlFolderDados+"POR_MUNICIPIOS_GERAL.txt");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			int codigoIBGE;
			int pib;
			int escola;
                        int populacao;
			String linha = br.readLine(); //pular a primeira linha
			
			while (br.ready()){ 
				linha = br.readLine(); 
				String[] fields = linha.split("\t");
				
				codigoIBGE = Integer.parseInt(fields[0]);
				pib = Integer.parseInt(fields[5]);
		                escola = Integer.parseInt(fields[10]);
                                populacao = Integer.parseInt(fields[15]);
				pibMap.put(codigoIBGE, pib);
				escolasMap.put(codigoIBGE, escola);
                                populacaoMap.put(codigoIBGE, populacao);
			} 
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void gerarJsonGeralEstado(Estado estado) {
		String fileName = myEstadoJsonGenerator.getNomeJsonGeral(myEstadoJsonGenerator.periodoAtual);
		
		myEstadoJsonGenerator.createFile(fileName, "{\n");

		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(estado.min_values, "MIN_Valores", true));
		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(estado.max_values, "MAX_Valores", true));
		
		
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