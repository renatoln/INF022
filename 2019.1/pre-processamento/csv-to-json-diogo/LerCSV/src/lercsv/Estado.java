/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lercsv;

/**
 *
 * @author digo_
 */
public class Estado {
    private Municipio[] municipios;
    public int[] registros;
    private Municipio municipioAtual;
    private Municipio vasio;
    private String nomeEstado;
    private String siglaEstado;
    private int idEstado;
    
    
    public Estado(){
        this.municipios = new Municipio[0];
    }
    public Estado(String siglaEstado, String nomeEstado, int codigo){
        this.siglaEstado = siglaEstado;
        this.idEstado = codigo;
        this.nomeEstado = nomeEstado;
        this.registros = new int[0];
        this.municipios = new Municipio[0];
        this.municipioAtual = new Municipio();
    }

    public void addMunicipio(String municipioAcentuado, String municipio, String codigo){
        Municipio[] municipioTemp = new Municipio[municipios.length+1];
        int[] registrosTemp = new int[registros.length+1];
        int i = 0;
        for(i = 0; i < this.municipios.length; i++){
            registrosTemp[i] = this.registros[i];
            municipioTemp[i] = this.municipios[i];
        }
        registrosTemp[i] = 0;
        municipioTemp[i] = new Municipio(municipioAcentuado, municipio, codigo);
        this.municipios = municipioTemp;
        this.registros = registrosTemp;
    }
    
    public String getSiglaEstado(){
        return this.siglaEstado;
    }
    public int getIDestado(){
        return this.idEstado;
    }
    public String getNomeEstado(){
        return this.nomeEstado;
    }
    public Municipio getMunicipio(String nomeMunicipio){
        if(this.municipioAtual.isEquals(nomeMunicipio)){
                return this.municipioAtual;                
        }
        for(int i = 0; i < this.municipios.length ; i++){
            this.municipioAtual = this.municipios[i];
            if(this.municipioAtual.isEquals(nomeMunicipio)){
                return this.municipioAtual;                
            }
        }
        return this.vasio;
    }
    public Municipio getMunicipio(int indice){
        return this.municipios[indice];
    }
    public int getQTDMunicipios(){
        return municipios.length;
    }
    
    public void gerarRegistros(){
        for(int i = 0; i < municipios.length; i++){
            registros[i] = municipios[i].getRegistros();   
        }
    }    
    public int registrosDoMunicipio(int indice){
        return municipios[indice].getRegistros();
    }
    
}
