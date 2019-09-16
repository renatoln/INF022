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

public class EstadoJsonEvolucao {

	HashMap<Integer, Mesorregiao> mesorregioes;
	HashMap<Integer, Microrregiao> microrregioes;
	HashMap<Integer, Municipio> municipios;

	EstadoJsonGenerator myEstadoJsonGenerator;

	public EstadoJsonEvolucao(EstadoJsonGenerator estadoJsonGeral) {
		myEstadoJsonGenerator = estadoJsonGeral;
		start();
	}

	private void start() {

		mesorregioes = myEstadoJsonGenerator.getMesorregioes();
		microrregioes = myEstadoJsonGenerator.getMicrorregioes();
		municipios = myEstadoJsonGenerator.getMunicipios();

		for (int i = 0; i < myEstadoJsonGenerator.periodos.length; i++)
			lerJsonGeral(myEstadoJsonGenerator.periodos[i]);
		
		Estado estado = new Estado(myEstadoJsonGenerator.codigoEstado, mesorregioes, microrregioes, municipios, true);

		gerarJsonEstadoEvolucao(estado, myEstadoJsonGenerator.periodos, myEstadoJsonGenerator.atributos);

	}

	void lerJsonGeral(String periodo) {
		String urlEspecifica = "/" + myEstadoJsonGenerator.getNomeJsonGeral(periodo);
		JSONObject jsonObjectsFileGeral = getJsonGeralLocal(urlEspecifica);

		// Mesorregioes
		JSONArray joMesos = jsonObjectsFileGeral.getJSONArray("MESORREGIOES");

		for (int i = 0; i < joMesos.length(); i++) {
			JSONObject jsonObj = joMesos.getJSONObject(i);

			ArrayList<Integer> valores = new ArrayList<Integer>();

			JSONArray valoresJsonArray = jsonObj.getJSONArray("VALORES");

			for (int j = 0; j < valoresJsonArray.length(); j++) {
				valores.add(valoresJsonArray.getInt(j));
			}
			Mesorregiao meso = mesorregioes.get(jsonObj.getInt("ID"));
			meso.addValoresEvolucao(periodo, valores);
		}

		// Microrregioes
		JSONArray joMicros = jsonObjectsFileGeral.getJSONArray("MICRORREGIOES");

		for (int i = 0; i < joMicros.length(); i++) {
			JSONObject jsonObj = joMicros.getJSONObject(i);

			ArrayList<Integer> valores = new ArrayList<Integer>();

			JSONArray valoresJsonArray = jsonObj.getJSONArray("VALORES");

			for (int j = 0; j < valoresJsonArray.length(); j++) {
				valores.add(valoresJsonArray.getInt(j));
			}
			Microrregiao micro = microrregioes.get(jsonObj.getInt("ID"));
			micro.addValoresEvolucao(periodo, valores);
		}

		// Microrregioes
		JSONArray joMuns = jsonObjectsFileGeral.getJSONArray("MUNICIPIOS");

		for (int i = 0; i < joMuns.length(); i++) {
			JSONObject jsonObj = joMuns.getJSONObject(i);

			ArrayList<Integer> valores = new ArrayList<Integer>();

			JSONArray valoresJsonArray = jsonObj.getJSONArray("VALORES");

			for (int j = 0; j < valoresJsonArray.length(); j++) {
				valores.add(valoresJsonArray.getInt(j));
			}
			Municipio mun = municipios.get(jsonObj.getInt("ID"));
			mun.addValoresEvolucao(periodo, valores);
		}

	}

	JSONObject getJsonGeralLocal(String urlEspecifica) {
		JSONObject jsonO = null;
		try {

			File file = new File(myEstadoJsonGenerator.urlFolderDados + urlEspecifica);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			String txt = response.toString();
			jsonO = (JSONObject) new JSONTokener(txt).nextValue();

			in.close();
			return jsonO;
		} catch (Exception e) {
			System.out.println(e);
		}

		return jsonO;
	}

	public void gerarJsonEstadoEvolucao(Estado estado, String periodos[], String atributos[]) {
		String fileName = myEstadoJsonGenerator.getNomeJsonEvolucao();

		myEstadoJsonGenerator.createFile(fileName, "{\n\t\"METADADOS\":{");
		
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t\"MESORREGIOES\":{");
		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(arrayListToIntVector(estado.mesorregioes_min_values_evolution), "MIN_VALORES", true));
		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(arrayListToIntVector(estado.mesorregioes_max_values_evolution), "MAX_VALORES", false));
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t\t},");
		
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t\"MICRORREGIOES\":{");
		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(arrayListToIntVector(estado.microrregioes_min_values_evolution), "MIN_VALORES", true));
		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(arrayListToIntVector(estado.microrregioes_max_values_evolution), "MAX_VALORES", false));
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t\t},");

		myEstadoJsonGenerator.appendTexto(fileName, "\n\t\"MUNICIPIOS\":{");
		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(arrayListToIntVector(estado.municipios_min_values_evolution), "MIN_VALORES", true));
		myEstadoJsonGenerator.appendTexto(fileName, list_int_values(arrayListToIntVector(estado.municipios_max_values_evolution), "MAX_VALORES", false));
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t\t}\n");
		
		myEstadoJsonGenerator.appendTexto(fileName, "\n\t},");
		

		// mesorregioes
		myEstadoJsonGenerator.appendTexto(fileName, "\n  \"MESORREGIOES\":[\n");

		String textoAppend;
		Collection<Mesorregiao> mesorregioes = estado.mesorregioes.values();
		int size = mesorregioes.size();
		int i = 0;
		for (Mesorregiao meso : mesorregioes) {
			textoAppend = "  {\n" + "    \"ID\": " + meso.ID + ",\n" + "    \"NOME_MESORREGIAO\": \""
					+ meso.NOME_MESORREGIAO + "\"," 
					+ list_AtributosEvolucao(meso.valoresEvolucao, periodos, atributos)
					+ " \n  }";

			if (i < size - 1)
				textoAppend += ",";

			textoAppend += "\n";

			myEstadoJsonGenerator.appendTexto(fileName, textoAppend);
			i++;
		}

		myEstadoJsonGenerator.appendTexto(fileName, "],");

		// microrregioes
		myEstadoJsonGenerator.appendTexto(fileName, "\n  \"MICRORREGIOES\":[\n");

		Collection<Microrregiao> microrregioes = estado.microrregioes.values();
		size = microrregioes.size();
		i = 0;
		for (Microrregiao micro : microrregioes) {
			textoAppend = "  {\n" + "    \"ID\": " + micro.ID + ",\n" + "    \"NOME_MICRORREGIAO\": \""
					+ micro.NOME_MICRORREGIAO + "\"," 
					+ list_AtributosEvolucao(micro.valoresEvolucao, periodos, atributos)
					+ ",\n" + "    \"ID_MESO\": " + micro.ID_MESO
					+ " \n  }";

			if (i < size - 1)
				textoAppend += ",";

			textoAppend += "\n";

			myEstadoJsonGenerator.appendTexto(fileName, textoAppend);
			i++;
		}

		myEstadoJsonGenerator.appendTexto(fileName, "],");

		// municipios
		myEstadoJsonGenerator.appendTexto(fileName, "\n  \"MUNICIPIOS\":[\n");

		Collection<Municipio> municipios = estado.municipios.values();
		size = municipios.size();
		i = 0;
		for (Municipio mun : municipios) {
			textoAppend = "  {\n" + "    \"ID\": " + mun.ID + ",\n" + "    \"NOME_MUNICIPIO\": \"" 
					+ mun.NOME_MUNICIPIO+ "\"," 
					+ list_AtributosEvolucao(mun.valoresEvolucao, periodos, atributos)
					+ ",\n" + "    \"ID_MESO\": " + mun.ID_MESO + ", \n"
					+ "    \"ID_MICRO\": " + mun.ID_MICRO 
					+ " \n" + "  }";

			if (i < size - 1)
				textoAppend += ",";

			textoAppend += "\n";

			myEstadoJsonGenerator.appendTexto(fileName, textoAppend);
			i++;
		}

		myEstadoJsonGenerator.appendTexto(fileName, "]\n");
		myEstadoJsonGenerator.appendTexto(fileName, "}");

	}

	private String list_AtributosEvolucao(HashMap<String, ArrayList<Integer>> valoresEvolucao, String periodos[], String atributos[]) {

		String s = "\"ATRIBUTOS\": [";
		String str = "\n\t" + s;

		int atributosMatrix[][] = new int[atributos.length][periodos.length];
		
		for (int i = 0; i < periodos.length; i++) {
			ArrayList<Integer> valoresPorPeriodo = valoresEvolucao.get(periodos[i]);
			for (int j = 0; j < atributos.length; j++) {
				atributosMatrix[j][i] = valoresPorPeriodo.get(j);
			}	
		}
		for (int j = 0; j < atributos.length; j++) {
			str += "\n\t\t{ \"NOME\": \""+ atributos[j]+"\",";
			str += list_int_values(atributosMatrix[j], "VALORES", false);
			str += "}"; 
			if (j + 1 < atributos.length) str += ","; 
		}	
		
		str += "\n\t]";

		return str;
	}

	private String list_int_values(int valores[], String s, boolean virgulaFinal) {
		
		String str = "\n\t\t\""+ s+ "\"";
		
		str += ": [";
		for (int i = 0; i < valores.length; i++) {
			str += valores[i];
			if (i < valores.length - 1)
				str += ", ";
		}
		str += "]";
		
		if (virgulaFinal) str += ",";
		
		return str;
	}
	
	private int[] arrayListToIntVector(ArrayList<Integer> listOrigem) {
		int size = listOrigem.size();
		int[] listDestino = new int [size];
		
		for (int i = 0; i < size; i ++ ) {
			listDestino[i] = listOrigem.get(i);
		}
		return listDestino;	
	}

}
