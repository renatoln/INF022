package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import model.Estado;
import model.Mesorregiao;
import model.Microrregiao;
import model.Municipio;

public class EstadoJsonGenerator {
	String urlBase = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";
	int idEstado = 29;
	String codigoEstado = "ba";
	String urlFolderDados = "dados/";
	HashMap<Integer, Integer> populacaoMap = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		EstadoJsonGenerator m = new EstadoJsonGenerator();
		
		HashMap<Integer, Mesorregiao> mesos = m.getMesorregioes();
		HashMap<Integer, Microrregiao> micros = m.getMicrorregioes();
		HashMap<Integer, Municipio> municipios = m.getMunicipios();
		
		m.addAtributosMunicipios(municipios);
		
		Estado estado = new Estado(m.codigoEstado, mesos, micros, municipios);
		
		m.gerarJsonEstados(estado);
		
	}
	
	private void addAtributosMunicipios(HashMap<Integer, Municipio> municipios) {
		//carregar populacao a partir de arquivo
		importPopulacaoPorMunicipio();
		
		Set<Integer> codigosIBGEMunicipios = municipios.keySet();
    	for (Integer codigoMun : codigosIBGEMunicipios)
    	{
    		if(codigoMun != null) {
    			Municipio mun = municipios.get(codigoMun);
    			int populacao = populacaoMap.get(codigoMun);
    			mun.addAtributo("População", populacao);
    			mun.addAtributo("PIB / pop", 258649/populacao);
    		}
    	}
		
	}

	HashMap<Integer, Mesorregiao> getMesorregioes() {
		String urlEspecifica = "/mesorregioes";
		ArrayList<JSONObject> joMesos = getJsonObjects(urlEspecifica);
		HashMap<Integer, Mesorregiao> mesos = new HashMap<Integer, Mesorregiao>();
		for (int i=0; i < joMesos.size(); i++) { 
			JSONObject jsonObj = joMesos.get(i);
			Mesorregiao meso = new Mesorregiao(jsonObj.getInt("id"), jsonObj.getString("nome"), -1);
			mesos.put(jsonObj.getInt("id"), meso);
		} 
		return mesos;
		
	}
	
	HashMap<Integer, Microrregiao> getMicrorregioes() {
		String urlEspecifica = "/microrregioes";
		ArrayList<JSONObject> joMicro = getJsonObjects(urlEspecifica);
		HashMap<Integer, Microrregiao> micros = new HashMap<Integer, Microrregiao>();
		for (int i=0; i < joMicro.size(); i++) { 
			JSONObject jsonObj = joMicro.get(i);
			Microrregiao micro = new Microrregiao(jsonObj.getInt("id"), jsonObj.getString("nome"), jsonObj.getJSONObject("mesorregiao").getInt("id"),-1);
			micros.put(jsonObj.getInt("id"), micro);
		} 
		return micros;
		
	}
	
	HashMap<Integer, Municipio> getMunicipios() {
		String urlEspecifica = "/municipios";
		ArrayList<JSONObject> joMun = getLocalJsonObjects("municipios-"+idEstado+"-ibge.json");
		HashMap<Integer, Municipio> muns = new HashMap<Integer, Municipio>();
		for (int i=0; i < joMun.size(); i++) { 
			JSONObject jsonObj = joMun.get(i);
			int municipioId = jsonObj.getInt("id");
			Municipio mun = new Municipio(municipioId, jsonObj.getString("nome"), jsonObj.getJSONObject("microrregiao").getJSONObject("mesorregiao").getInt("id"), jsonObj.getJSONObject("microrregiao").getInt("id"), -1);
			muns.put(municipioId, mun);
		} 
		return muns;
		
	}
	
	ArrayList<JSONObject> getLocalJsonObjects(String urlEspecifica){
		ArrayList<JSONObject> jsonArray=new ArrayList<JSONObject>();
	
		try {
				
			File file = new File(urlFolderDados+urlEspecifica);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				JSONArray jsonArr = (JSONArray)new JSONTokener(inputLine).nextValue();
				
				for (int i=0; i < jsonArr.length(); i++) {
					JSONObject jsonObj = jsonArr.getJSONObject(i);
					jsonArray.add(jsonObj);
				}    
				
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
			
		return jsonArray;
	}
	
	ArrayList<JSONObject> getJsonObjects(String urlEspecifica){
		ArrayList<JSONObject> jsonArray=new ArrayList<JSONObject>();
	
		try {
				
			URL obj = new URL(urlBase+idEstado+urlEspecifica);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				JSONArray jsonArr = (JSONArray)new JSONTokener(inputLine).nextValue();
				
				for (int i=0; i < jsonArr.length(); i++) {
					JSONObject jsonObj = jsonArr.getJSONObject(i);
					jsonArray.add(jsonObj);
				}    
				
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
			
		return jsonArray;
	}
	
	
	public void importPopulacaoPorMunicipio(){
		try {
			File file = new File(urlFolderDados+"municipio-populacao.txt");
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
	
	public void gerarJsonEstados(Estado estado) {
		String fileName = "ba_2019-06_geral.json";
		
		createFile(fileName, "{\n");

		appendTexto(fileName, list_int_values(estado.min_values, "MIN_Valores", true));
		appendTexto(fileName, list_int_values(estado.max_values, "MAX_Valores", true));
		
		
		//mesorregioes
		appendTexto(fileName, "\n\"MESORREGIOES\":[\n");
		
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
			
			appendTexto(fileName, textoAppend);
			i++;
    	}
		
		
		appendTexto(fileName, "],");
		
		//microrregioes
		appendTexto(fileName, "\n\"MICRORREGIOES\":[\n");
		
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
			
			appendTexto(fileName, textoAppend);
			i++;
    	}
		
		
		appendTexto(fileName, "],");
		
		//municipios
		appendTexto(fileName, "\n\"MUNICIPIOS\":[\n");
		
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
			
			appendTexto(fileName, textoAppend);
			i++;
    	}
		
		
		appendTexto(fileName, "]\n");
		appendTexto(fileName, "}");
		
		
	}
	
	private String list_int_values(ArrayList<Integer> valores, String s, boolean virgulaFinal) {
		
		String str = "\n\t\""+ s+ "\"";
		
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
		
		String str = "\n\t\""+ s + "\"";
		
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
	
	private void createFile(String fileName, String inicialText) {
		
        try { 
            BufferedWriter out = new BufferedWriter( 
                          new FileWriter(urlFolderDados+fileName)); 
            out.write(inicialText); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("Exception Occurred" + e); 
        } 
	}
	
	private void appendTexto(String fileName, String textoAppend) {
		try { 
			  
            // Open given file in append mode. 
            BufferedWriter out = new BufferedWriter( 
                   new FileWriter(urlFolderDados+fileName, true)); 
            out.write(textoAppend); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("exception occoured" + e); 
        }
		
	}
}