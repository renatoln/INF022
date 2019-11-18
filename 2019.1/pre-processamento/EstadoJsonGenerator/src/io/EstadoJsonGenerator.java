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
import java.time.Period;
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
	
	/*Dados de diabetes/sifilis*/
	//static String periodos[] = {"2004", "2005", "2006", "2007", "2008","2009", "2010", "2011", "2012", "2013"};
	//String atributos[] = {"População","Diabetes Tipo 2", "Sífilis Congênita", "Sífilis Gestacional"};

	/*Dados de médicos*/
	//static String periodos[] = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"};
	//String atributos[] = {"QtProfissionais/100000Hab","QtMédicos/100000Hab", "QtEnfermeiros/100000Hab", "QtSUS/100000Hab", "QtNãoSUS/100000Hab"};
	
	/*SEFINRA*/
	//static String periodos[] = {"2019"};
	//String atributos[] = {"População","PIB"};
	
	/*Dados de JUCEB mes a mes*/
	//static String periodos[] = {"05-2019", "06-2019", "07-2019", "08-2019", "09-2019", "10-2019"};
	//String atributos[] = {"Empresas Abertas","Empresas Fechadas", "Balanço"};
	
	/*Dados de JUCEB ano a ano*/
	static String periodos[] = {"2013", "2014", "2015", "2016", "2017", "2018", "2019"};
	String atributos[] = {"Empresas Abertas","Empresas Fechadas", "Balanço"};
	
	String urlFolderDados = "dados/";

	public static void main(String[] args) {
		for (int i = 0; i < periodos.length; i ++) {
			EstadoJsonGenerator ejGenerator = new EstadoJsonGenerator();
			new EstadoJsonGeral(ejGenerator, i);
		}
		new EstadoJsonEvolucao(new EstadoJsonGenerator());
	}
	
	HashMap<Integer, Mesorregiao> getMesorregioes() {
		String urlEspecifica = "/mesorregioes";
		//ArrayList<JSONObject> joMesos = getJsonObjects(urlEspecifica);
		ArrayList<JSONObject> joMesos = getLocalJsonObjects("mesorregioes-"+idEstado+"-ibge.json");
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
		//ArrayList<JSONObject> joMicro = getJsonObjects(urlEspecifica);
		ArrayList<JSONObject> joMicro = getLocalJsonObjects("microrregioes-"+idEstado+"-ibge.json");
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
	
	String getNomeJsonGeral(String strPeriodo) {
		return codigoEstado+"_"+strPeriodo+"_geral.json";
		
	}
	
	String getNomeJsonEvolucao() {
		return codigoEstado+"_evolucao.json";
		
	}
	
	public void createFile(String fileName, String inicialText) {
		
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
	
	public void appendTexto(String fileName, String textoAppend) {
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