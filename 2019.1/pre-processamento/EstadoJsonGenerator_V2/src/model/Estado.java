package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
//import org.apache.commons.math3.stat.StatUtils;


public class Estado {
	
	public String sigla = "ba";
	public String nome_capital = "Salvador";
	public int codigo_capital = 2927408;
	public ArrayList<Integer>  min_values = new ArrayList<Integer>();
	public ArrayList<Integer>  max_values = new ArrayList<Integer>();
	
        public String periodos[] = {"2012", "2013", "2014", "2015", "2016"};
        public String atributos [] = {"População", "PIB", "PIB / pop", "Escolas"};
        String urlFolderDados = "C:\\Users\\Dagoberto\\Documents\\JS\\GIT\\INF022_pre_processamento\\2019.1\\EstadoJsonGenerator\\src\\dados\\";
        public String arquivoDeDados = "POR_MUNICIPIOS_GERAL.txt"; //coloque aqui o nome e extenção do arquivo com os dados a serem coletados.
        
        private final String A = "Otimo";
        private final String B = "Razoavel";
        private final String C = "Ruim";
	
	public HashMap<Integer, Mesorregiao> mesorregioes;
	public HashMap<Integer, Microrregiao> microrregioes;
	public HashMap<Integer, Municipio> municipios;
	
        public static ArrayList<String> CATEGORIA = new ArrayList<String>();
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
		//setCategoria(max_values, min_values, muns);
		atualizaAtributosMicros();
		atualizaAtributosMesos();
                setCategoriaMunicipios(max_values, min_values, municipios);
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
                
            for (Integer codigoMun : codigosIBGEMunicipios){
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
        //Aqui editar a lógica para setar categorias
        private void setCategoriaMunicipios(ArrayList<Integer>  max_values, ArrayList<Integer>  min_values, 
                                    HashMap<Integer, Municipio> mun) {
            try{
                Integer categA = 0;
                Integer categB = 0;
                int qtdAtributos = ATRIBUTOS.size();
                int codigoIBGE;
                
                File file = new File(urlFolderDados+arquivoDeDados);
                BufferedReader br = new BufferedReader(new FileReader(file)); 
                String linha = br.readLine(); //pular a primeira linha
                    while (br.ready()){ 
                        linha = br.readLine(); 
                        String[] fields = linha.split("\t");
                        codigoIBGE = Integer.parseInt(fields[0]);
                        for (int i=0; i< qtdAtributos; i++){
                            categA = (((max_values.get(i) - min_values.get(i)/3)*2)+ min_values.get(i) );
                            categB = ((max_values.get(i) - min_values.get(i)/3)+ min_values.get(i) );
                            
                            if(mun.get(codigoIBGE).VALORES.get(i) >= categA)
                                mun.get(codigoIBGE).CATEGORIA.add(A);
                            else if(mun.get(codigoIBGE).VALORES.get(i)>= categB)
                                mun.get(codigoIBGE).CATEGORIA.add(B);
                            else
                                mun.get(codigoIBGE).CATEGORIA.add(C);
                        }
                    }
                    br.close();
            }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            }
}
