package model;

import java.util.ArrayList;

public class Municipio {
	
	public int ID;
	public String NOME_MUNICIPIO;
	public int ID_MESO;
	public int ID_MICRO;
	public int POPULACAO;
	public int VALOR;
	public ArrayList<String> CATEGORIA = new ArrayList<String>();
	public ArrayList<String> ATRIBUTOS = new ArrayList<String>();
	public ArrayList<Integer> VALORES = new ArrayList<Integer>();
	
    public Municipio(int id, String nome_mun, int id_meso, int id_micro, int populacao){
    	ID = id;
    	NOME_MUNICIPIO = nome_mun;
      	ID_MESO = id_meso;
      	ID_MICRO = id_micro;
        POPULACAO = populacao;	
    }
    
    public void addAtributo(String nome, int valor) {
    	ATRIBUTOS.add(nome);
    	VALORES.add(valor);
    }
	

}
